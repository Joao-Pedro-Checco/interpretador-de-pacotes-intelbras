package br.com.fulltime.fullarm.infra.processor.event;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.builder.factory.EventBuilderFactory;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import br.com.fulltime.fullarm.infra.constants.Qualifier;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventProcessorImpl implements EventProcessor {
    private final EventBuilderFactory eventBuilderFactory;
    private final ChecksumValidator checksumValidator;

    public EventProcessorImpl(EventBuilderFactory eventBuilderFactory, ChecksumValidator checksumValidator) {
        this.eventBuilderFactory = eventBuilderFactory;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        List<String> bytes = splitBytes(userInputRawContent);
        System.out.println("Montando pacote de Evento...");
        System.out.println("===================================================================================");

        ConnectionType connectionType = parseConnectionType(bytes);
        System.out.println("Adicionando Tipo de Conexão: " + connectionType);

        String account = parseAccount(bytes);
        System.out.println("Adicionando Número da conta: " + account);

        String contactId = parseContactId(bytes);
        System.out.println("Adicionando Contact Id: " + contactId);

        Qualifier qualifier = parseQualifier(bytes);
        System.out.println("Adicionando Qualificador: " + qualifier);

        String eventCode = parseEventCode(bytes);
        System.out.println("Adicionando Código do Evento: " + eventCode);

        String partition = parsePartition(bytes);
        System.out.println("Adicionando Partição: " + partition);

        String argument = parseArgument(bytes);
        System.out.println("Adicionando Argumento: " + argument);
        System.out.println("===================================================================================");

        return eventBuilderFactory.build()
                .withConnectionType(connectionType)
                .withAccount(account)
                .withContactId(contactId)
                .withQualifier(qualifier)
                .withEventCode(eventCode)
                .withPartition(partition)
                .withArgument(argument)
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
        boolean isEvent = PackageIdentifier.getByValue(identifierByte) == PackageIdentifier.EVENT;
        if (!isEvent) {
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

    private ConnectionType parseConnectionType(List<String> bytes) {
        String connectionType = bytes.get(2);
        return ConnectionType.getByValue(connectionType);
    }

    private String parseAccount(List<String> bytes) {
        List<String> accountBytes = handleByte0A(bytes.subList(3, 7));
        List<String> handledAccountBytes = accountBytes.stream()
                .map(b -> b.equals("00") ? "0" : b.replace("0", ""))
                .collect(Collectors.toList());
        return String.join("", handledAccountBytes);
    }

    private String parseContactId(List<String> bytes) {
        int start = 7;
        int end = 9;
        return convertHexListToDecimalString(bytes, start, end);
    }

    private Qualifier parseQualifier(List<String> bytes) {
        String qualifierByte = bytes.get(9);
        return Qualifier.getByValue(qualifierByte);
    }

    private String parseEventCode(List<String> bytes) {
        int start = 10;
        int end = 13;
        return convertHexListToDecimalString(bytes, start, end);
    }

    private String parsePartition(List<String> bytes) {
        int start = 13;
        int end = 15;
        return convertHexListToDecimalString(bytes, start, end);
    }

    private String parseArgument(List<String> bytes) {
        int start = 15;
        int end = 18;
        return convertHexListToDecimalString(bytes, start, end);
    }

    private String convertHexListToDecimalString(List<String> bytes, int start, int end) {
        List<String> handledBytes = handleByte0A(bytes.subList(start, end));
        List<String> convertedBytes = handledBytes.stream()
                .map(b -> String.valueOf(Integer.valueOf(b, 16)))
                .collect(Collectors.toList());
        return String.join("", convertedBytes);
    }

    private List<String> handleByte0A(List<String> bytes) {
        return bytes.stream().map(b -> b.equals("0A") ? "00" : b)
                .collect(Collectors.toList());
    }
}
