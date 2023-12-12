package br.com.fulltime.fullarm.infra.processor.authentication;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.builder.factory.AuthenticationBuilderFactory;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationProcessorImpl implements AuthenticationProcessor {
    private final AuthenticationBuilderFactory authenticationBuilderFactory;
    private final ChecksumValidator checksumValidator;

    public AuthenticationProcessorImpl(AuthenticationBuilderFactory authenticationBuilderFactory, ChecksumValidator checksumValidator) {
        this.authenticationBuilderFactory = authenticationBuilderFactory;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        List<String> bytes = splitBytes(userInputRawContent);

        System.out.println("Montando pacote de Autenticação...");
        System.out.println("===================================================================================");

        ConnectionType connectionType = parseConnectionType(bytes);
        System.out.println("Adicionando Tipo de Conexão: " + connectionType);

        String account = parseAccount(bytes);
        System.out.println("Adicionando Número da conta: " + account);

        String macAddress = parseMacAddress(bytes);
        System.out.println("Adicionando Endereço MAC: " + macAddress);
        System.out.println("===================================================================================");

        return authenticationBuilderFactory.build()
                .withConnectionType(connectionType)
                .withAccount(account)
                .withMacAddress(macAddress)
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
        boolean isAuthentication = PackageIdentifier.getByValue(identifierByte) == PackageIdentifier.AUTHENTICATION;
        if (!isAuthentication) {
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
        String connectionByte = bytes.get(2);
        return ConnectionType.getByValue(connectionByte);
    }

    private String parseAccount(List<String> bytes) {
        List<String> accountBytes = bytes.subList(3, 5);
        return String.join("", accountBytes);
    }

    private String parseMacAddress(List<String> bytes) {
        List<String> macAddressBytes = bytes.subList(5, 8);
        return String.join("", macAddressBytes);
    }
}
