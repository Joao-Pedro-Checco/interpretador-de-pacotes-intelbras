package br.com.fulltime.fullarm.core.message.builder.status;

import br.com.fulltime.fullarm.core.message.status.components.PowerInfo;

public class PowerInfoBuilder {
    private final PowerInfo instance;

    public PowerInfoBuilder() {
        this.instance = new PowerInfo();
    }

    public PowerInfoBuilder withLackOfElectricNetwork(boolean lackOfElectricNetwork) {
        this.instance.setLackOfElectricNetwork(lackOfElectricNetwork);
        return this;
    }

    public PowerInfoBuilder withLowBattery(boolean lowBattery) {
        this.instance.setBatteryIsLow(lowBattery);
        return this;
    }

    public PowerInfoBuilder withAbsentOrInvertedBattery(boolean absentOrInvertedBattery) {
        this.instance.setBatteryIsAbsentOrInverted(absentOrInvertedBattery);
        return this;
    }

    public PowerInfoBuilder withShortCircuitOnBattery(boolean shortCircuitOnBattery) {
        this.instance.setBatteryHasShortCircuit(shortCircuitOnBattery);
        return this;
    }

    public PowerInfoBuilder withAuxiliaryOutputOverload(boolean auxiliaryOutputOverload) {
        this.instance.setHasOverloadOnAuxiliaryOutlet(auxiliaryOutputOverload);
        return this;
    }

    public PowerInfo build() {
        return instance;
    }
}
