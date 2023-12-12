package br.com.fulltime.fullarm.core.message.builder.status;

import br.com.fulltime.fullarm.core.message.status.components.CentralInfo;

public class CentralInfoBuilder {
    private final CentralInfo instance;

    public CentralInfoBuilder() {
        this.instance = new CentralInfo();
    }

    public CentralInfoBuilder withDetectedProblems(boolean detectedProblems) {
        this.instance.setDetectedProblems(detectedProblems);
        return this;
    }

    public CentralInfoBuilder withSirenIsOn(boolean sirenIsOn) {
        this.instance.setSirenIsOn(sirenIsOn);
        return this;
    }

    public CentralInfoBuilder withThereAreSetOffZones(boolean thereAreSetOffZones) {
        this.instance.setThereAreSetOffZones(thereAreSetOffZones);
        return this;
    }

    public CentralInfoBuilder withCentralIsActivated(boolean centralIsActivated) {
        this.instance.setCentralIsActivated(centralIsActivated);
        return this;
    }

    public CentralInfo build() {
        return instance;
    }
}
