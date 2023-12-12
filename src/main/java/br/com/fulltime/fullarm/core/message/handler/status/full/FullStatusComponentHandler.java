package br.com.fulltime.fullarm.core.message.handler.status.full;

import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FullStatusComponentHandler {
    public String handleZoneInfo(ZoneInfo zoneInfo) {
        List<Integer> openZones = zoneInfo.getOpenZones();
        List<Integer> violatedZones = zoneInfo.getViolatedZones();
        List<Integer> annulledZones = zoneInfo.getAnnulledZones();
        List<Integer> tamperedZones = zoneInfo.getTamperedZones();
        List<Integer> shortCircuitedZones = zoneInfo.getShortCircuitedZones();
        List<Integer> zonesWithLowBatteryOnSensor = zoneInfo.getZonesWithLowBatteryOnSensor();

        return "Informações das Zonas" +
                (!openZones.isEmpty() ? "\n   - Zonas abertas -> " + openZones : "\n   - Não existem zonas abertas") +
                (!violatedZones.isEmpty() ? "\n   - Zonas violadas -> " + violatedZones : "\n   - Não existem zonas violadas") +
                (!annulledZones.isEmpty() ? "\n   - Zonas anuladas -> " + annulledZones : "\n   - Não existem zonas anuladas") +
                (!tamperedZones.isEmpty() ? "\n   - Zonas com tamper -> " + tamperedZones : "\n   - Não existem zonas com tamper") +
                (!shortCircuitedZones.isEmpty() ? "\n   - Zonas com curto-circuito -> " + shortCircuitedZones
                        : "\n   - Não existem zonas com curto-circuito") +
                (!zonesWithLowBatteryOnSensor.isEmpty() ? "\n   - Zonas com bateria baixa no sensor sem fio -> "
                        + zonesWithLowBatteryOnSensor : "\n   - Não existem zonas com bateria baixa no sensor sem fio");
    }

    public String handleCentralModel(CentralModel centralModel) {
        return "Modelo da central: " + centralModel;
    }

    public String handleFirmwareVersion(String firmwareVersion) {
        return "Versão do firmware: " + firmwareVersion;
    }

    public String handlePartitionStatus(PartitionStatus partitionStatus) {
        return "Status de partição: " + partitionStatus;
    }

    public String handlePartitions(List<Partition> partitions) {
        Partition partition1 = partitions.get(0);
        Partition partition2 = partitions.get(0);
        String partition1IsActivatedText = partition1.isActivated() ? "Ativada" : "Desativada";
        String partition2IsActivatedText = partition2.isActivated() ? "Ativada" : "Desativada";

        return "Partições" +
                "\n   - Partição A -> " + partition1IsActivatedText +
                "\n   - Partição B ->  " + partition2IsActivatedText;
    }

    public String handleCentralInfo(CentralInfo centralInfo) {
        if (!centralInfo.hasProblems()) return "Informações da Central" + "\n   - Não existem problemas";

        return "Informações da Central" +
                (centralInfo.hasDetectedProblems() ? "\n   - Problemas detectados" : "") +
                (centralInfo.sirenIsOn() ? "\n   - Sirene ligada" : "") +
                (centralInfo.thereAreSetOffZones() ? "\n   - Existem zonas disparadas" : "") +
                (centralInfo.centralIsActivated() ? "\n   - Central está ativada" : "");
    }

    public String handleDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Data e hora -> " + dateTime.format(dateTimeFormatter);
    }

    public String handlePowerInfo(PowerInfo powerInfo) {
        if (!powerInfo.hasProblems()) return "Informações de energia" + "\n   - Não existem problemas";

        return "Informações de energia" +
                (powerInfo.hasLackOfElectricNetwork() ? "\n   - Falta de rede elétrica" : "") +
                (powerInfo.batteryIsLow() ? "\n   - Bateria fraca" : "") +
                (powerInfo.batteryIsAbsentOrInverted() ? "\n   - Bateria ausente ou invertida" : "") +
                (powerInfo.batteryHasShortCircuit() ? "\n   - Bateria com curto-circuito" : "") +
                (powerInfo.hasOverloadOnAuxiliaryOutlet() ? "\n   - Sobrecarga na saída auxiliar" : "");
    }

    public String handleKeyboards(List<Keyboard> keyboards) {
        StringBuilder resultString = new StringBuilder("Teclados");
        int keyboardNumber = 1;

        for (Keyboard keyboard : keyboards) {
            resultString.append("\n   - Teclado ").append(keyboardNumber)
                    .append("\n       - Problema: ").append(keyboard.hasProblem() ? "sim" : "não")
                    .append(" | ").append("Tamper: ").append(keyboard.isTampered() ? "sim" : "não");
            keyboardNumber++;
        }

        return resultString.toString();
    }

    public String handleReceivers(List<Receiver> receivers) {
        StringBuilder resultString = new StringBuilder("Receptores");
        int receiverNumber = 1;

        for (Receiver receiver : receivers) {
            resultString.append("\n   - Receptor ").append(receiverNumber)
                    .append(" -> ").append("Problema: ").append(receiver.hasProblem() ? "sim" : "não");
            receiverNumber++;
        }

        return resultString.toString();
    }

    public String handleBattery(Battery battery) {
        return "Bateria" +
                "\n   - Contorno " + (battery.outlineIsOn() ? "ligado" : "desligado") +
                "\n   - Nível 1 " + (battery.levelOneIsOn() ? "ligado" : "desligado") +
                "\n   - Nível 2 " + (battery.levelTwoIsOn() ? "ligado" : "desligado") +
                "\n   - Nível 3 " + (battery.levelThreeIsOn() ? "ligado" : "desligado") +
                "\n   - Contorno -> " + (battery.outlineIsSetToBlink() ? "piscar" : "não piscar") +
                "\n   - Nível 1 -> " + (battery.levelOneIsSetToBlink() ? "piscar" : "não piscar") +
                "\n   - Nível 2 -> " + (battery.levelTwoIsSetToBlink() ? "piscar" : "não piscar") +
                "\n   - Nível 3 -> " + (battery.levelThreeIsSetToBlink() ? "piscar" : "não piscar");
    }

    public String handlePgmExpanders(List<Expander> expanders) {
        StringBuilder resultString = new StringBuilder("Expansores de PGM");
        int expanderNumber = 1;

        for (Expander expander : expanders) {
            resultString.append("\n   - Expansor ").append(expanderNumber)
                    .append(" -> ").append("Problema: ").append(expander.hasProblem() ? "sim" : "não");
            expanderNumber++;
        }

        return resultString.toString();
    }

    public String handleZoneExpanders(List<Expander> expanders) {
        StringBuilder resultString = new StringBuilder("Expansores de Zonas");
        int expanderNumber = 1;

        for (Expander expander : expanders) {
            resultString.append("\n   - Expansor ").append(expanderNumber)
                    .append(" -> ").append("Problema: ").append(expander.hasProblem() ? "sim" : "não");
            expanderNumber++;
        }

        return resultString.toString();
    }

    public String handleSirenInfo(SirenInfo sirenInfo) {
        return "Informações da sirene" +
                "\n   - Sirene " + (sirenInfo.isSirenOn() ? "ligada" : "desligada") +
                (sirenInfo.isSirenWireCut() ? "\n   - Corte no fio da sirene" : "") +
                (sirenInfo.isSirenWireOnShortCircuit() ? "\n   - Curto-circuito no fio da sirene" : "");
    }

    public String handleCommunicationProblems(boolean phoneLineIsCut, boolean failedToCommunicateEvent) {
        if (!phoneLineIsCut && !failedToCommunicateEvent) {
            return "Problemas de comunicação" + "\n   - Nenhum problema detectado";
        }

        return "Problemas de comunicação" +
                (phoneLineIsCut ? "\n   - Corte de linha telefônica" : "") +
                (failedToCommunicateEvent ? "\n   - Falha ao comunicar evento" : "");
    }

    public String handlePgms(List<Pgm> pgms) {
        StringBuilder resultString = new StringBuilder("PGMs");
        int pgmNumber = 1;

        for (Pgm pgm : pgms) {
            resultString.append("\n   - PGM ").append(pgmNumber)
                    .append(" -> ").append(pgm.isTurnedOn() ? "ligada" : "desligada");
            pgmNumber++;
        }

        return resultString.toString();
    }
}
