package br.com.fulltime.fullarm.core.message.status.components;

public class Partition {
    private boolean isActivated;

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "isActivated=" + isActivated +
                '}';
    }
}
