package br.com.fulltime.fullarm.core.message.status.components;

public class SirenInfo {
    private boolean sirenOn;
    private boolean sirenWireCut;
    private boolean sirenWireOnShortCircuit;

    public boolean isSirenOn() {
        return sirenOn;
    }

    public boolean isSirenWireCut() {
        return sirenWireCut;
    }

    public boolean isSirenWireOnShortCircuit() {
        return sirenWireOnShortCircuit;
    }

    public void setSirenOn(boolean sirenOn) {
        this.sirenOn = sirenOn;
    }

    public void setSirenWireCut(boolean sirenWireCut) {
        this.sirenWireCut = sirenWireCut;
    }

    public void setSirenWireOnShortCircuit(boolean sirenWireOnShortCircuit) {
        this.sirenWireOnShortCircuit = sirenWireOnShortCircuit;
    }

    @Override
    public String toString() {
        return "SirenInfo{" +
                "sirenIsOn=" + sirenOn +
                ", sirenWireWasCut=" + sirenWireCut +
                ", sirenWireHasShortCircuit=" + sirenWireOnShortCircuit +
                '}';
    }
}
