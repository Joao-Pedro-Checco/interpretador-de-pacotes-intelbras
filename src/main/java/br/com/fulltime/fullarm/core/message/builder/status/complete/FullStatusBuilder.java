package br.com.fulltime.fullarm.core.message.builder.status.complete;

import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.core.message.status.full.FullStatusMessage;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class FullStatusBuilder {
    private final FullStatusMessage instance;

    public FullStatusBuilder() {
        this.instance = new FullStatusMessage();
    }

    public FullStatusBuilder withZoneInfo(ZoneInfo zoneInfo) {
        this.instance.setInformacoesZonas(zoneInfo);
        return this;
    }

    public FullStatusBuilder withCentralModel(CentralModel centralModel) {
        this.instance.setModeloCentral(centralModel);
        return this;
    }

    public FullStatusBuilder withFirmwareVersion(String firmwareVersion) {
        this.instance.setFirmwareVersion(firmwareVersion);
        return this;
    }

    public FullStatusBuilder withPartitionStatus(PartitionStatus partitionStatus) {
        this.instance.setStatusParticao(partitionStatus);
        return this;
    }

    public FullStatusBuilder withPartitions(List<Partition> partitions) {
        this.instance.setPartitions(partitions);
        return this;
    }

    public FullStatusBuilder withCentralInfo(CentralInfo centralInfo) {
        this.instance.setInformacoesCentral(centralInfo);
        return this;
    }

    public FullStatusBuilder withDateTime(LocalDateTime dateTime) {
        this.instance.setDateTime(dateTime);
        return this;
    }

    public FullStatusBuilder withPowerInfo(PowerInfo powerInfo) {
        this.instance.setInformacoesEnergia(powerInfo);
        return this;
    }

    public FullStatusBuilder withKeyboards(List<Keyboard> keyboards) {
        this.instance.setTeclados(keyboards);
        return this;
    }

    public FullStatusBuilder withReceivers(List<Receiver> receivers) {
        this.instance.setReceivers(receivers);
        return this;
    }

    public FullStatusBuilder withBattery(Battery battery) {
        this.instance.setBateria(battery);
        return this;
    }

    public FullStatusBuilder withPgmExpanders(List<Expander> pgmExpanders) {
        this.instance.setPgmExpanders(pgmExpanders);
        return this;
    }

    public FullStatusBuilder withZoneExpanders(List<Expander> zoneExpander) {
        this.instance.setZoneExpanders(zoneExpander);
        return this;
    }

    public FullStatusBuilder withSirenInfo(SirenInfo sirenInfo) {
        this.instance.setInformacoesSirene(sirenInfo);
        return this;
    }

    public FullStatusBuilder withPhoneLineIsCut(boolean phoneLineIsCut) {
        this.instance.setPhoneLineIsCut(phoneLineIsCut);
        return this;
    }

    public FullStatusBuilder withFailedToCommunicateEvent(boolean failedToCommunicateEvent) {
        this.instance.setFailedToCommunicateEvent(failedToCommunicateEvent);
        return this;
    }

    public FullStatusBuilder withPgms(List<Pgm> pgms) {
        this.instance.setPgms(pgms);
        return this;
    }

    public FullStatusMessage build() {
        return instance;
    }
}
