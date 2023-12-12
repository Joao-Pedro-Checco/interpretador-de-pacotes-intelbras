package br.com.fulltime.fullarm.core;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.infra.processor.PackageProcessor;
import br.com.fulltime.fullarm.infra.processor.ProcessorFactory;
import org.springframework.stereotype.Service;

@Service
public class PackageInterpreter {
    private final ProcessorFactory processorFactory;

    public PackageInterpreter(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    public GenericPackageMessage interpretPackage(PackageUserInput packageUserInput) {
        return processorFactory.getProcessor(packageUserInput).process(packageUserInput);
    }
}
