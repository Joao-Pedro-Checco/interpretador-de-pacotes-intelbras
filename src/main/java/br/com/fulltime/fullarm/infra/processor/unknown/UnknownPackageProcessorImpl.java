package br.com.fulltime.fullarm.infra.processor.unknown;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.unknown.UnknownPackageMessage;
import org.springframework.stereotype.Service;

@Service
public class UnknownPackageProcessorImpl implements UnknownPackageProcessor {
    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        return new UnknownPackageMessage();
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        return true;
    }
}
