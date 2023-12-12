package br.com.fulltime.fullarm.core.message.status.components;

public class Keyboard {
    private boolean hasProblem;
    private boolean isTampered;

    public boolean hasProblem() {
        return hasProblem;
    }

    public boolean isTampered() {
        return isTampered;
    }

    public void setHasProblem(boolean hasProblem) {
        this.hasProblem = hasProblem;
    }

    public void setTampered(boolean tampered) {
        this.isTampered = tampered;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "hasProblem=" + hasProblem +
                ", isTampered=" + isTampered +
                '}';
    }
}
