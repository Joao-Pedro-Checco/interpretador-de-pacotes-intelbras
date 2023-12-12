package br.com.fulltime.fullarm.core.message.status.components;

public class Expander {
    private boolean hasProblem;

    public boolean hasProblem() {
        return hasProblem;
    }

    public void setHasProblem(boolean hasProblem) {
        this.hasProblem = hasProblem;
    }

    @Override
    public String toString() {
        return "Expander{" +
                "hasProblem=" + hasProblem +
                '}';
    }
}
