package br.com.fulltime.fullarm.core.message.builder.status;

import br.com.fulltime.fullarm.core.message.status.components.SirenInfo;

public class SirenInfoBuilder {
    private final SirenInfo instance;

    public SirenInfoBuilder() {
        this.instance = new SirenInfo();
    }

    public SirenInfoBuilder withSirenIsOn(boolean sirenIsOn) {
        this.instance.setSirenOn(sirenIsOn);
        return this;
    }

    public SirenInfoBuilder withSirenWireIsCut(boolean sirenWireIsCut) {
        this.instance.setSirenWireCut(sirenWireIsCut);
        return this;
    }

    public SirenInfoBuilder withShorCircuitOnSirenWire(boolean shorCircuitOnSirenWire) {
        this.instance.setSirenWireOnShortCircuit(shorCircuitOnSirenWire);
        return this;
    }

    public SirenInfo build() {
        return instance;
    }
}
