package br.com.fulltime.fullarm.infra.processor.command;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.builder.factory.CommandBuilderFactory;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.SubcommandProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.SubcommandProcessorFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandProcessorImpl implements CommandProcessor {
    private final SubcommandProcessorFactory subcommandProcessorFactory;
    private final CommandBuilderFactory commandBuilderFactory;
    private final ChecksumValidator checksumValidator;
    private static final String DATA_DELIMITER = "21";
    private static final List<String> validPasswordCharacters = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private int passwordSize;

    public CommandProcessorImpl(SubcommandProcessorFactory subcommandProcessorFactory,
                                CommandBuilderFactory commandBuilderFactory,
                                ChecksumValidator checksumValidator) {
        this.subcommandProcessorFactory = subcommandProcessorFactory;
        this.commandBuilderFactory = commandBuilderFactory;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        List<String> bytes = splitBytes(userInputRawContent);
        System.out.println("Montando pacote de Comando...");
        System.out.println("===================================================================================");

        String password = parsePassword(bytes.subList(3, bytes.size() - 2));
        System.out.println("Adicionando Senha: " + password);

        Subcommand subcommand = parseSubcommand(bytes);
        System.out.println("Adicionando SubComando: " + subcommand);
        System.out.println("===================================================================================");

        return commandBuilderFactory.build()
                .withPassword(password)
                .withSubcommand(subcommand)
                .build();
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        List<String> bytes = splitBytes(userInputRawString);
        boolean packageSizeIsValid = (bytes.size() - 2) == Integer.parseInt(bytes.get(0), 16);
        if (!packageSizeIsValid) {
            return false;
        }

        String identifierByte = bytes.get(1);
        boolean isCommand = PackageIdentifier.getByValue(identifierByte) == PackageIdentifier.COMMAND;
        if (!isCommand) {
            return false;
        }

        String delimiterByte = bytes.get(2);
        if (!delimiterByte.equals(DATA_DELIMITER)) {
            return false;
        }

        boolean checksumIsValid = checksumValidator.checksumIsValid(userInputRawString);
        if (!checksumIsValid) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private String parsePassword(List<String> bytes) {
        List<String> password = bytes.stream().map(this::parseHexToAscii)
                .filter(validPasswordCharacters::contains)
                .collect(Collectors.toList());
        password = getPasswordBytes(password);

        this.passwordSize = password.size();

        return String.join("", password);
    }

    private static List<String> getPasswordBytes(List<String> password) {
        if (password.size() >= 6) {
            return password.subList(0, 6);
        }
        return password.subList(0, 4);
    }

    private String parseHexToAscii(String passwordBytes) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < passwordBytes.length(); i += 2) {
            String str = passwordBytes.substring(i, i + 2);
            output.append((char)Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    private String extractSubcommandBytes(List<String> bytes) {
        int startOfSubcommand = 3 + passwordSize;
        List<String> subcommandBytes = new ArrayList<>();
        for (int i = startOfSubcommand; !bytes.get(i).equals(DATA_DELIMITER); i++) {
            subcommandBytes.add(bytes.get(i));
        }

        return String.join(" ", subcommandBytes);
    }

    private Subcommand parseSubcommand(List<String> bytes) {
        String subcommandBytes = extractSubcommandBytes(bytes);
        SubcommandProcessor subcommandProcessor = subcommandProcessorFactory.getProcessor(subcommandBytes);
        return subcommandProcessor.process(subcommandBytes);
    }
}
