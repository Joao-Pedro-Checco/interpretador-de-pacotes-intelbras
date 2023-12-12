package br.com.fulltime.fullarm.core.message.status.components;

public class Pgm {
    private boolean turnedOn;

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

    @Override
    public String toString() {
        return "Pgm{" +
                "isTurnedOn=" + turnedOn +
                '}';
    }
}
