package br.com.fulltime.fullarm.infra.processor.keepalive;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.keepalive.KeepAliveMessage;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveProcessorImpl implements KeepAliveProcessor {
    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        System.out.println("Processando mensagem Keep Alive...");
        System.out.println("===================================================================================");

        return new KeepAliveMessage();
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        boolean packageSizeIsValid = userInputRawString.length() == 2;
        if (!packageSizeIsValid) {
            return false;
        }

        boolean packageIdentifierIsValid = PackageIdentifier.getByValue(userInputRawString) == PackageIdentifier.KEEP_ALIVE;
        if (!packageIdentifierIsValid) {
            return false;
        }

        return true;
    }
}
