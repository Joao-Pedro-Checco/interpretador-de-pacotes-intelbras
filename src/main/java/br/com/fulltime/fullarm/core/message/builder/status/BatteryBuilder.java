package br.com.fulltime.fullarm.core.message.builder.status;

import br.com.fulltime.fullarm.core.message.status.components.Battery;

public class BatteryBuilder {
    private final Battery instance;

    public BatteryBuilder() {
        this.instance = new Battery();
    }

    public BatteryBuilder withOutlineIsOn(boolean outlineIsOn) {
        this.instance.setOutlineIsOn(outlineIsOn);
        return this;
    }

    public BatteryBuilder withLevelOneIsOn(boolean levelOneIsOn) {
        this.instance.setLevelOneIsOn(levelOneIsOn);
        return this;
    }

    public BatteryBuilder withLevelTwoIsOn(boolean levelTwoIsOn) {
        this.instance.setLevelTwoIsOn(levelTwoIsOn);
        return this;
    }

    public BatteryBuilder withLevelThreeIsOn(boolean levelThreeIsOn) {
        this.instance.setLevelThreeIsOn(levelThreeIsOn);
        return this;
    }

    public BatteryBuilder withOutlineIsSetToBlink(boolean outlineIsSetToBlink) {
        this.instance.setOutlineIsSetToBlink(outlineIsSetToBlink);
        return this;
    }

    public BatteryBuilder withLevelOneIsSetToBlink(boolean levelOneIsSetToBlink) {
        this.instance.setLevelOneIsSetToBlink(levelOneIsSetToBlink);
        return this;
    }

    public BatteryBuilder withLevelTwoIsSetToBlink(boolean levelTwoIsSetToBlink) {
        this.instance.setLevelTwoIsSetToBlink(levelTwoIsSetToBlink);
        return this;
    }

    public BatteryBuilder withLevelThreeIsSetToBlink(boolean levelThreeIsSetToBlink) {
        this.instance.setLevelThreeIsSetToBlink(levelThreeIsSetToBlink);
        return this;
    }

    public Battery build() {
        return instance;
    }
}
