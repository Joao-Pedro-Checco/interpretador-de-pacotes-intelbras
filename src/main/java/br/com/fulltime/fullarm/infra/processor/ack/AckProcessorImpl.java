package br.com.fulltime.fullarm.infra.processor.ack;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.ack.AckMessage;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import org.springframework.stereotype.Service;

@Service
public class AckProcessorImpl implements AckProcessor {
    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        System.out.println("Processando pacote ACK...");
        System.out.println("==============================================");

        return new AckMessage();
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        boolean packageSizeIsValid = userInputRawString.length() == 2;
        if (!packageSizeIsValid) {
            return false;
        }

        boolean packageIdentifierIsValid = PackageIdentifier.getByValue(userInputRawString) == PackageIdentifier.ACK;
        if (!packageIdentifierIsValid) {
            return false;
        }

        return true;
    }
}
