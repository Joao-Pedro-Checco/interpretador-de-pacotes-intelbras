package br.com.fulltime.fullarm.core.message.builder.status;

import br.com.fulltime.fullarm.core.message.status.components.ZoneInfo;

import java.util.List;

public class ZoneInfoBuilder {
    private final ZoneInfo instance;

    public ZoneInfoBuilder() {
        this.instance = new ZoneInfo();
    }

    public ZoneInfoBuilder withOpenZones(List<Integer> openZones) {
        this.instance.setOpenZones(openZones);
        return this;
    }

    public ZoneInfoBuilder withViolatedZones(List<Integer> violatedZones) {
        this.instance.setViolatedZones(violatedZones);
        return this;
    }

    public ZoneInfoBuilder withAnnulledZones(List<Integer> annulledZones) {
        this.instance.setAnnulledZones(annulledZones);
        return this;
    }

    public ZoneInfoBuilder withTamperedZones(List<Integer> tamperedZones) {
        this.instance.setTamperedZones(tamperedZones);
        return this;
    }

    public ZoneInfoBuilder withShortCircuitedZones(List<Integer> shorCircuitZones) {
        this.instance.setShortCircuitedZones(shorCircuitZones);
        return this;
    }

    public ZoneInfoBuilder withZonesWithLowBatteryOnSensor(List<Integer> zonesWithLowBatteryOnSensor) {
        this.instance.setZonesWithLowBatteryOnSensor(zonesWithLowBatteryOnSensor);
        return this;
    }

    public ZoneInfo build() {
        return instance;
    }
}
