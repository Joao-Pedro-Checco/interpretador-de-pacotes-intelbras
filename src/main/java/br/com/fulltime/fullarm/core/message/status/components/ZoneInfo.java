package br.com.fulltime.fullarm.core.message.status.components;

import java.util.List;

public class ZoneInfo {
    private List<Integer> openZones;
    private List<Integer> violatedZones;
    private List<Integer> annulledZones;
    private List<Integer> tamperedZones;
    private List<Integer> shortCircuitedZones;
    private List<Integer> zonesWithLowBatteryOnSensor;

    public List<Integer> getOpenZones() {
        return openZones;
    }

    public List<Integer> getViolatedZones() {
        return violatedZones;
    }

    public List<Integer> getAnnulledZones() {
        return annulledZones;
    }

    public List<Integer> getTamperedZones() {
        return tamperedZones;
    }

    public List<Integer> getShortCircuitedZones() {
        return shortCircuitedZones;
    }

    public List<Integer> getZonesWithLowBatteryOnSensor() {
        return zonesWithLowBatteryOnSensor;
    }

    public void setOpenZones(List<Integer> openZones) {
        this.openZones = openZones;
    }

    public void setViolatedZones(List<Integer> violatedZones) {
        this.violatedZones = violatedZones;
    }

    public void setAnnulledZones(List<Integer> annulledZones) {
        this.annulledZones = annulledZones;
    }

    public void setTamperedZones(List<Integer> tamperedZones) {
        this.tamperedZones = tamperedZones;
    }

    public void setShortCircuitedZones(List<Integer> shortCircuitedZones) {
        this.shortCircuitedZones = shortCircuitedZones;
    }

    public void setZonesWithLowBatteryOnSensor(List<Integer> zonesWithLowBatteryOnSensor) {
        this.zonesWithLowBatteryOnSensor = zonesWithLowBatteryOnSensor;
    }

    @Override
    public String toString() {
        return "ZoneInfo{" +
                "openZones=" + openZones +
                ", violatedZones=" + violatedZones +
                ", annulledZones=" + annulledZones +
                ", tamperedZones=" + tamperedZones +
                ", shortCircuitedZones=" + shortCircuitedZones +
                ", zonesWithLowBatteryOnSensor=" + zonesWithLowBatteryOnSensor +
                '}';
    }
}
