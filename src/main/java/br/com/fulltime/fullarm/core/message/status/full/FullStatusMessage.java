package br.com.fulltime.fullarm.core.message.status.full;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class FullStatusMessage extends GenericPackageMessage {
    private ZoneInfo zoneInfo;
    private CentralModel centralModel;
    private String firmwareVersion;
    private PartitionStatus partitionStatus;
    private List<Partition> partitions;
    private CentralInfo centralInfo;
    private LocalDateTime dateTime;
    private PowerInfo powerInfo;
    private List<Keyboard> keyboards;
    private List<Receiver> receivers;
    private Battery battery;
    private List<Expander> PgmExpanders;
    private List<Expander> ZoneExpanders;
    private SirenInfo sirenInfo;
    private boolean phoneLineIsCut;
    private boolean failedToCommunicateEvent;
    private List<Pgm> pgms;

    public FullStatusMessage() {
        super(MessageType.FULL_STATUS);
    }

    public ZoneInfo getZoneInfo() {
        return zoneInfo;
    }

    public CentralModel getCentralModel() {
        return centralModel;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public PartitionStatus getPartitionStatus() {
        return partitionStatus;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public CentralInfo getCentralInfo() {
        return centralInfo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public PowerInfo getPowerInfo() {
        return powerInfo;
    }

    public List<Keyboard> getKeyboards() {
        return keyboards;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public Battery getBattery() {
        return battery;
    }

    public List<Expander> getPgmExpanders() {
        return PgmExpanders;
    }

    public List<Expander> getZoneExpanders() {
        return ZoneExpanders;
    }

    public SirenInfo getSirenInfo() {
        return sirenInfo;
    }

    public boolean isPhoneLineIsCut() {
        return phoneLineIsCut;
    }

    public boolean isFailedToCommunicateEvent() {
        return failedToCommunicateEvent;
    }

    public List<Pgm> getPgms() {
        return pgms;
    }

    public void setInformacoesZonas(ZoneInfo zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    public void setModeloCentral(CentralModel centralModel) {
        this.centralModel = centralModel;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setStatusParticao(PartitionStatus partitionStatus) {
        this.partitionStatus = partitionStatus;
    }

    public void setPartitions(List<Partition> partitions) {
        this.partitions = partitions;
    }

    public void setInformacoesCentral(CentralInfo centralInfo) {
        this.centralInfo = centralInfo;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setInformacoesEnergia(PowerInfo powerInfo) {
        this.powerInfo = powerInfo;
    }

    public void setTeclados(List<Keyboard> keyboards) {
        this.keyboards = keyboards;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public void setBateria(Battery battery) {
        this.battery = battery;
    }

    public void setPgmExpanders(List<Expander> pgmExpanders) {
        this.PgmExpanders = pgmExpanders;
    }

    public void setZoneExpanders(List<Expander> zoneExpanders) {
        this.ZoneExpanders = zoneExpanders;
    }

    public void setInformacoesSirene(SirenInfo sirenInfo) {
        this.sirenInfo = sirenInfo;
    }

    public void setPhoneLineIsCut(boolean phoneLineIsCut) {
        this.phoneLineIsCut = phoneLineIsCut;
    }

    public void setFailedToCommunicateEvent(boolean failedToCommunicateEvent) {
        this.failedToCommunicateEvent = failedToCommunicateEvent;
    }

    public void setPgms(List<Pgm> pgms) {
        this.pgms = pgms;
    }

    @Override
    public String toString() {
        return "FullStatus{" +
                "zoneInfo=" + zoneInfo +
                ", centralModel=" + centralModel +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", partitionStatus=" + partitionStatus +
                ", partitions=" + partitions +
                ", centralInfo=" + centralInfo +
                ", dateTime=" + dateTime +
                ", powerInfo=" + powerInfo +
                ", keyboards=" + keyboards +
                ", receivers=" + receivers +
                ", battery=" + battery +
                ", PgmExpanders=" + PgmExpanders +
                ", ZoneExpanders=" + ZoneExpanders +
                ", sirenInfo=" + sirenInfo +
                ", phoneLineIsCut=" + phoneLineIsCut +
                ", failedToCommunicateEvent=" + failedToCommunicateEvent +
                ", pgms=" + pgms +
                ", type=" + type +
                '}';
    }
}
