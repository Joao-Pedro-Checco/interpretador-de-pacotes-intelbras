package br.com.fulltime.fullarm.core.message.status.components;

public class CentralInfo {
    private boolean detectedProblems;
    private boolean sirenIsOn;
    private boolean thereAreSetOffZones;
    private boolean centralIsActivated;

    public boolean hasDetectedProblems() {
        return detectedProblems;
    }

    public boolean sirenIsOn() {
        return sirenIsOn;
    }

    public boolean thereAreSetOffZones() {
        return thereAreSetOffZones;
    }

    public boolean centralIsActivated() {
        return centralIsActivated;
    }

    public boolean hasProblems() {
        return hasDetectedProblems() || sirenIsOn() || thereAreSetOffZones() || centralIsActivated();
    }

    public void setDetectedProblems(boolean detectedProblems) {
        this.detectedProblems = detectedProblems;
    }

    public void setSirenIsOn(boolean sirenIsOn) {
        this.sirenIsOn = sirenIsOn;
    }

    public void setThereAreSetOffZones(boolean thereAreSetOffZones) {
        this.thereAreSetOffZones = thereAreSetOffZones;
    }

    public void setCentralIsActivated(boolean centralIsActivated) {
        this.centralIsActivated = centralIsActivated;
    }

    @Override
    public String toString() {
        return "CentralInfo{" +
                "detectedProblems=" + detectedProblems +
                ", sirenIsOn=" + sirenIsOn +
                ", thereAreSetOffZones=" + thereAreSetOffZones +
                ", centralIsActivated=" + centralIsActivated +
                '}';
    }
}
