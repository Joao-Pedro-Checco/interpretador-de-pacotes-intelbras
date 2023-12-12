package br.com.fulltime.fullarm.core.message.status.components;

public class Receiver {
    private boolean hasProblem;

    public boolean hasProblem() {
        return hasProblem;
    }

    public void setHasProblem(boolean hasProblem) {
        this.hasProblem = hasProblem;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "hasProblem=" + hasProblem +
                '}';
    }
}
