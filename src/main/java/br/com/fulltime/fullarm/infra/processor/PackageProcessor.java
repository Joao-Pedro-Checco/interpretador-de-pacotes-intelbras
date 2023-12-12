package br.com.fulltime.fullarm.infra.processor;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

public interface PackageProcessor {
    GenericPackageMessage process(PackageUserInput packageUserInput);

    boolean canProcess(String userInputRawString);
}
