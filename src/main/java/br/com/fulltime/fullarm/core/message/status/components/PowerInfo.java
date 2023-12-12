package br.com.fulltime.fullarm.core.message.status.components;

public class PowerInfo {
    private boolean lackOfElectricNetwork;
    private boolean batteryIsLow;
    private boolean batteryIsAbsentOrInverted;
    private boolean batteryHasShortCircuit;
    private boolean hasOverloadOnAuxiliaryOutlet;

    public boolean hasLackOfElectricNetwork() {
        return lackOfElectricNetwork;
    }

    public boolean batteryIsLow() {
        return batteryIsLow;
    }

    public boolean batteryIsAbsentOrInverted() {
        return batteryIsAbsentOrInverted;
    }

    public boolean batteryHasShortCircuit() {
        return batteryHasShortCircuit;
    }

    public boolean hasOverloadOnAuxiliaryOutlet() {
        return hasOverloadOnAuxiliaryOutlet;
    }

    public boolean hasProblems() {
        return hasLackOfElectricNetwork() || batteryIsLow() || batteryIsAbsentOrInverted() ||
                batteryHasShortCircuit() || hasOverloadOnAuxiliaryOutlet();
    }

    public void setLackOfElectricNetwork(boolean lackOfElectricNetwork) {
        this.lackOfElectricNetwork = lackOfElectricNetwork;
    }

    public void setBatteryIsLow(boolean batteryIsLow) {
        this.batteryIsLow = batteryIsLow;
    }

    public void setBatteryIsAbsentOrInverted(boolean batteryIsAbsentOrInverted) {
        this.batteryIsAbsentOrInverted = batteryIsAbsentOrInverted;
    }

    public void setBatteryHasShortCircuit(boolean batteryHasShortCircuit) {
        this.batteryHasShortCircuit = batteryHasShortCircuit;
    }

    public void setHasOverloadOnAuxiliaryOutlet(boolean hasOverloadOnAuxiliaryOutlet) {
        this.hasOverloadOnAuxiliaryOutlet = hasOverloadOnAuxiliaryOutlet;
    }

    @Override
    public String toString() {
        return "PowerInfo{" +
                "lackOfElectricNetwork=" + lackOfElectricNetwork +
                ", batteryIsLow=" + batteryIsLow +
                ", batteryIsAbsentOrInverted=" + batteryIsAbsentOrInverted +
                ", batteryHasShortCircuit=" + batteryHasShortCircuit +
                ", hasOverloadOnAuxiliaryOutlet=" + hasOverloadOnAuxiliaryOutlet +
                '}';
    }
}
