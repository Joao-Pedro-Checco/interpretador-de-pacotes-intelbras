package br.com.fulltime.fullarm.core.message.builder.status.partial;

import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.core.message.status.partial.PartialStatusMessage;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PartialStatusBuilder {
    private final PartialStatusMessage instance;

    public PartialStatusBuilder() {
        this.instance = new PartialStatusMessage();
    }

    public PartialStatusBuilder withZoneInfo(ZoneInfo zoneInfo) {
        this.instance.setInformacoesZonas(zoneInfo);
        return this;
    }

    public PartialStatusBuilder withCentralModel(CentralModel centralModel) {
        this.instance.setModeloCentral(centralModel);
        return this;
    }

    public PartialStatusBuilder withFirmwareVersion(String firmwareVersion) {
        this.instance.setFirmwareVersion(firmwareVersion);
        return this;
    }

    public PartialStatusBuilder withPartitionStatus(PartitionStatus partitionStatus) {
        this.instance.setStatusParticao(partitionStatus);
        return this;
    }

    public PartialStatusBuilder withPartitions(List<Partition> partitions) {
        this.instance.setPartitions(partitions);
        return this;
    }

    public PartialStatusBuilder withCentralInfo(CentralInfo centralInfo) {
        this.instance.setInformacoesCentral(centralInfo);
        return this;
    }

    public PartialStatusBuilder withDateTime(LocalDateTime dateTime) {
        this.instance.setDateTime(dateTime);
        return this;
    }

    public PartialStatusBuilder withPowerInfo(PowerInfo powerInfo) {
        this.instance.setInformacoesEnergia(powerInfo);
        return this;
    }

    public PartialStatusBuilder withKeyboards(List<Keyboard> keyboards) {
        this.instance.setTeclados(keyboards);
        return this;
    }

    public PartialStatusBuilder withReceivers(List<Receiver> receivers) {
        this.instance.setReceivers(receivers);
        return this;
    }

    public PartialStatusBuilder withBattery(Battery battery) {
        this.instance.setBateria(battery);
        return this;
    }

    public PartialStatusBuilder withSirenInfo(SirenInfo sirenInfo) {
        this.instance.setInformacoesSirene(sirenInfo);
        return this;
    }

    public PartialStatusBuilder withPhoneLineIsCut(boolean phoneLineIsCut) {
        this.instance.setPhoneLineIsCut(phoneLineIsCut);
        return this;
    }

    public PartialStatusBuilder withFailedToCommunicateEvent(boolean failedToCommunicateEvent) {
        this.instance.setFailedToCommunicateEvent(failedToCommunicateEvent);
        return this;
    }

    public PartialStatusBuilder withPgms(List<Pgm> pgms) {
        this.instance.setPgms(pgms);
        return this;
    }

    public PartialStatusMessage build() {
        return instance;
    }
}
