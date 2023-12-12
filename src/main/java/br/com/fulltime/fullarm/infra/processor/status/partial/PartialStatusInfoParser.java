package br.com.fulltime.fullarm.infra.processor.status.partial;

import br.com.fulltime.fullarm.core.message.builder.factory.*;
import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;
import br.com.fulltime.fullarm.infra.processor.BitManager;
import br.com.fulltime.fullarm.infra.processor.ZoneManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Service
public class PartialStatusInfoParser {
    private final ZoneInfoBuilderFactory zoneInfoBuilderFactory;
    private final CentralInfoBuilderFactory centralInfoBuilderFactory;
    private final BatteryBuilderFactory batteryBuilderFactory;
    private final PowerInfoBuilderFactory powerInfoBuilderFactory;
    private final SirenInfoBuilderFactory sirenInfoBuilderFactory;
    private final ZoneManager zoneManager;

    public PartialStatusInfoParser(ZoneInfoBuilderFactory zoneInfoBuilderFactory,
                                   CentralInfoBuilderFactory centralInfoBuilderFactory,
                                   BatteryBuilderFactory batteryBuilderFactory,
                                   PowerInfoBuilderFactory powerInfoBuilderFactory,
                                   SirenInfoBuilderFactory sirenInfoBuilderFactory,
                                   ZoneManager zoneManager) {
        this.zoneInfoBuilderFactory = zoneInfoBuilderFactory;
        this.centralInfoBuilderFactory = centralInfoBuilderFactory;
        this.batteryBuilderFactory = batteryBuilderFactory;
        this.powerInfoBuilderFactory = powerInfoBuilderFactory;
        this.sirenInfoBuilderFactory = sirenInfoBuilderFactory;
        this.zoneManager = zoneManager;
    }

    public List<Integer> parseZonesWithBitOn1(List<String> subList) {
        return zoneManager.parseZonesWithBitOn1(subList);
    }

    public ZoneInfo parseZoneInfo(List<String> openZonesBytes,
                                  List<String> violatedZonesBytes,
                                  List<String> annulledBytesZones,
                                  List<String> tamperedZonesBytes,
                                  List<String> shortCircuitedZonesBytes,
                                  List<String> batteryStatusZonesBytes) {
        return zoneInfoBuilderFactory.build()
                .withOpenZones(parseZonesWithBitOn1(openZonesBytes))
                .withViolatedZones(parseZonesWithBitOn1(violatedZonesBytes))
                .withAnnulledZones(parseZonesWithBitOn1(annulledBytesZones))
                .withTamperedZones(parseZonesWithBitOn1(tamperedZonesBytes))
                .withShortCircuitedZones(parseZonesWithBitOn1(shortCircuitedZonesBytes))
                .withZonesWithLowBatteryOnSensor(parseZonesWithBitOn1(batteryStatusZonesBytes))
                .build();
    }

    public CentralModel parseCentralModel(String modelByte) {
        return CentralModel.getByValue(modelByte);
    }

    public String parseFirmwareVersion(String firmwareByte) {
        return String.format("%s.%s", firmwareByte.charAt(0), firmwareByte.charAt(1));
    }

    public PartitionStatus parsePartitionStatus(String partitionStatusByte) {
        return PartitionStatus.getByValue(partitionStatusByte);
    }

    public List<Partition> parsePartitions(String partitionBytes) {
        char[] partitionNibbles = partitionBytes.toCharArray();
        List<Partition> partitions = new ArrayList<>();

        for (char partitionNibble : partitionNibbles) {
            Partition partition = new Partition();
            partition.setActivated(partitionNibble == '1');
            partitions.add(partition);
        }

        return partitions;
    }

    public CentralInfo parseCentralInfo(String centralInfoByte) {
        List<Boolean> centralInfoBits = convertHexByteToBooleanList(centralInfoByte);
        return centralInfoBuilderFactory.build()
                .withDetectedProblems(centralInfoBits.get(0))
                .withSirenIsOn(centralInfoBits.get(1))
                .withThereAreSetOffZones(centralInfoBits.get(2))
                .withCentralIsActivated(centralInfoBits.get(3))
                .build();
    }

    public LocalDateTime parseDateTime(List<String> dateTimeBytes) {
        int hour = Integer.parseInt(dateTimeBytes.get(0), 16);
        int minutes = Integer.parseInt(dateTimeBytes.get(1), 16);
        int day = Integer.parseInt(dateTimeBytes.get(2), 16);
        int month = Integer.parseInt(dateTimeBytes.get(3), 16);
        int year = Integer.parseInt(dateTimeBytes.get(4), 16) + 2000;

        return LocalDateTime.of(year, month, day, hour, minutes);
    }

    public Battery parseBattery(String batteryByte) {
        BitSet batteryStatusBits = BitManager.convertHexByteToBitSet(batteryByte);
        return batteryBuilderFactory.build()
                .withOutlineIsOn(batteryStatusBits.get(0))
                .withLevelOneIsOn(batteryStatusBits.get(1))
                .withLevelTwoIsOn(batteryStatusBits.get(2))
                .withLevelThreeIsOn(batteryStatusBits.get(3))
                .withOutlineIsSetToBlink(batteryStatusBits.get(4))
                .withLevelOneIsSetToBlink(batteryStatusBits.get(5))
                .withLevelTwoIsSetToBlink(batteryStatusBits.get(6))
                .withLevelThreeIsSetToBlink(batteryStatusBits.get(7))
                .build();
    }

    public List<Boolean> convertHexByteToBooleanList(String byteHex) {
        BitSet bitSet = BitManager.convertHexByteToBitSet(byteHex);
        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            booleanList.add(bitSet.get(i));
        }

        return booleanList;
    }

    public PowerInfo parsePowerInfo(String powerInfoByte) {
        List<Boolean> powerInfoBits = convertHexByteToBooleanList(powerInfoByte);
        return powerInfoBuilderFactory.build()
                .withLackOfElectricNetwork(powerInfoBits.get(0))
                .withLowBattery(powerInfoBits.get(1))
                .withAbsentOrInvertedBattery(powerInfoBits.get(2))
                .withShortCircuitOnBattery(powerInfoBits.get(3))
                .withAuxiliaryOutputOverload(powerInfoBits.get(4))
                .build();
    }

    public List<Keyboard> parseKeyboards(String keyboardProblemsByte, String tamperedKeyboardByte) {
        List<Keyboard> keyboards = new ArrayList<>();
        BitSet keyboardProblemsBits = BitManager.convertHexByteToBitSet(keyboardProblemsByte);
        BitSet tamperedKeyboardBits = BitManager.convertHexByteToBitSet(tamperedKeyboardByte);
        for (int i = 0; i < 4; i++) {
            Keyboard keyboard = new Keyboard();
            keyboard.setHasProblem(keyboardProblemsBits.get(i));
            keyboard.setTampered(tamperedKeyboardBits.get(i + 4));
            keyboards.add(keyboard);
        }

        return keyboards;
    }

    public List<Receiver> parseReceivers(String receiverProblemsByte) {
        List<Receiver> receivers = new ArrayList<>();
        BitSet receiverProblemsBits = BitManager.convertHexByteToBitSet(receiverProblemsByte);
        for (int i = 0; i < 4; i++) {
            Receiver receiver = new Receiver();
            receiver.setHasProblem(receiverProblemsBits.get(i + 4));
            receivers.add(receiver);
        }

        return receivers;
    }

    public List<Pgm> parsePgms(String pgmInfoByte) {
        List<Pgm> pgms = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Pgm pgm = new Pgm();
            Boolean pgmIsTurnedOn = convertHexByteToBooleanList(pgmInfoByte).get(6 - 1);
            pgm.setTurnedOn(pgmIsTurnedOn);
            pgms.add(pgm);
        }

        return pgms;
    }

    public SirenInfo sirenInfoParser(String sirenIsTurnedOnByte, String sirenInfoByte) {
        Boolean sirenIsTurnedOnBit = convertHexByteToBooleanList(sirenIsTurnedOnByte).get(2);
        Boolean sirenWireIsCutBit = convertHexByteToBooleanList(sirenInfoByte).get(0);
        Boolean sirenIsShortCircuitedBit = convertHexByteToBooleanList(sirenInfoByte).get(1);

        return sirenInfoBuilderFactory.build()
                .withSirenIsOn(sirenIsTurnedOnBit)
                .withSirenWireIsCut(sirenWireIsCutBit)
                .withShorCircuitOnSirenWire(sirenIsShortCircuitedBit)
                .build();
    }
}
