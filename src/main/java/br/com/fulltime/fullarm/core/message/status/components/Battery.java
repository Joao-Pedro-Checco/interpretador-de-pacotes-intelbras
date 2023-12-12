package br.com.fulltime.fullarm.core.message.status.components;

public class Battery {
    private boolean outlineIsOn;
    private boolean levelOneIsOn;
    private boolean levelTwoIsOn;
    private boolean levelThreeIsOn;
    private boolean outlineIsSetToBlink;
    private boolean levelOneIsSetToBlink;
    private boolean levelTwoIsSetToBlink;
    private boolean levelThreeIsSetToBlink;

    public boolean outlineIsOn() {
        return outlineIsOn;
    }

    public boolean levelOneIsOn() {
        return levelOneIsOn;
    }

    public boolean levelTwoIsOn() {
        return levelTwoIsOn;
    }

    public boolean levelThreeIsOn() {
        return levelThreeIsOn;
    }

    public boolean outlineIsSetToBlink() {
        return outlineIsSetToBlink;
    }

    public boolean levelOneIsSetToBlink() {
        return levelOneIsSetToBlink;
    }

    public boolean levelTwoIsSetToBlink() {
        return levelTwoIsSetToBlink;
    }

    public boolean levelThreeIsSetToBlink() {
        return levelThreeIsSetToBlink;
    }

    public void setOutlineIsOn(boolean outlineIsOn) {
        this.outlineIsOn = outlineIsOn;
    }

    public void setLevelOneIsOn(boolean levelOneIsOn) {
        this.levelOneIsOn = levelOneIsOn;
    }

    public void setLevelTwoIsOn(boolean levelTwoIsOn) {
        this.levelTwoIsOn = levelTwoIsOn;
    }

    public void setLevelThreeIsOn(boolean levelThreeIsOn) {
        this.levelThreeIsOn = levelThreeIsOn;
    }

    public void setOutlineIsSetToBlink(boolean outlineIsSetToBlink) {
        this.outlineIsSetToBlink = outlineIsSetToBlink;
    }

    public void setLevelOneIsSetToBlink(boolean levelOneIsSetToBlink) {
        this.levelOneIsSetToBlink = levelOneIsSetToBlink;
    }

    public void setLevelTwoIsSetToBlink(boolean levelTwoIsSetToBlink) {
        this.levelTwoIsSetToBlink = levelTwoIsSetToBlink;
    }

    public void setLevelThreeIsSetToBlink(boolean levelThreeIsSetToBlink) {
        this.levelThreeIsSetToBlink = levelThreeIsSetToBlink;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "outlineIsOn=" + outlineIsOn +
                ", levelOneIsOn=" + levelOneIsOn +
                ", levelTwoIsOn=" + levelTwoIsOn +
                ", levelThreeIsOn=" + levelThreeIsOn +
                ", outlineIsSetToBlink=" + outlineIsSetToBlink +
                ", levelOneIsSetToBlink=" + levelOneIsSetToBlink +
                ", levelTwoIsSetToBlink=" + levelTwoIsSetToBlink +
                ", levelThreeIsSetToBlink=" + levelThreeIsSetToBlink +
                '}';
    }
}
