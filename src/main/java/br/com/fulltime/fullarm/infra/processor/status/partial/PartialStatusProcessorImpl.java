package br.com.fulltime.fullarm.infra.processor.status.partial;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.builder.factory.PartialStatusBuilderFactory;
import br.com.fulltime.fullarm.core.message.status.components.*;
import br.com.fulltime.fullarm.infra.constants.CentralModel;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import br.com.fulltime.fullarm.infra.constants.PartitionStatus;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PartialStatusProcessorImpl implements PartialStatusProcessor {
    private final PartialStatusInfoParser partialStatusInfoParser;
    private final PartialStatusBuilderFactory partialStatusBuilderFactory;
    private final ChecksumValidator checksumValidator;

    public PartialStatusProcessorImpl(PartialStatusInfoParser partialStatusInfoParser,
                                      PartialStatusBuilderFactory partialStatusBuilderFactory,
                                      ChecksumValidator checksumValidator) {
        this.partialStatusInfoParser = partialStatusInfoParser;
        this.partialStatusBuilderFactory = partialStatusBuilderFactory;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        List<String> bytes = splitBytes(userInputRawContent);

        System.out.println("Montando status parcial...");
        System.out.println("===================================================================================");
        List<String> statusBytes = bytes.subList(2, bytes.size() - 1);
        List<String> openZonesBytes = statusBytes.subList(0, 6);
        List<String> violatedZonesBytes = statusBytes.subList(6, 12);
        List<String> annulledZonesBytes = statusBytes.subList(12, 18);
        List<String> tamperedZonesBytes = statusBytes.subList(33, 35);
        List<String> shortCircuitedZonesBytes = statusBytes.subList(35, 37);
        List<String> batteryStatusZonesBytes = statusBytes.subList(38, 43);
        ZoneInfo zoneInfo = partialStatusInfoParser.parseZoneInfo(openZonesBytes, violatedZonesBytes,
                        annulledZonesBytes, tamperedZonesBytes, shortCircuitedZonesBytes, batteryStatusZonesBytes);
        System.out.println("Adicionando Informações das zonas: " + zoneInfo);

        CentralModel centralModel = partialStatusInfoParser.parseCentralModel(statusBytes.get(18));
        System.out.println("Adicionando Modelo da Central: " + centralModel);

        String firmwareVersion = partialStatusInfoParser.parseFirmwareVersion(statusBytes.get(19));
        System.out.println("Adicionando Versão do Firmware: " + firmwareVersion);

        PartitionStatus partitionStatus = partialStatusInfoParser.parsePartitionStatus(statusBytes.get(20));
        System.out.println("Adicionando Status de partição: " + partitionStatus);

        String partitionByte = statusBytes.get(21);
        List<Partition> partitions = partialStatusInfoParser.parsePartitions(partitionByte);
        System.out.println("Adicionando Partições: " + partitions);

        CentralInfo centralInfo = partialStatusInfoParser.parseCentralInfo(statusBytes.get(22));
        System.out.println("Adicionando Informações da Central: " + centralInfo);

        LocalDateTime dateTime = partialStatusInfoParser.parseDateTime(statusBytes.subList(23, 28));
        System.out.println("Adicionando Data e Hora: " + dateTime);

        PowerInfo powerInfo = partialStatusInfoParser.parsePowerInfo(statusBytes.get(28));
        System.out.println("Adicionando Informações de energia: " + powerInfo);

        String keyboardProblemBytes = statusBytes.get(29);
        String tamperedKeyboardBytes = statusBytes.get(31);
        List<Keyboard> keyboards = partialStatusInfoParser.parseKeyboards(keyboardProblemBytes, tamperedKeyboardBytes);
        System.out.println("Adicionando Teclados: " + keyboards);

        String receiverProblemBytes = statusBytes.get(29);
        List<Receiver> receivers = partialStatusInfoParser.parseReceivers(receiverProblemBytes);
        System.out.println("Adicionando Receptores: " + receivers);

        Battery battery = partialStatusInfoParser.parseBattery(statusBytes.get(30));
        System.out.println("Adicionando Informações da Bateria: " + battery);

        String sirenIsTurnedOnByte = statusBytes.get(37);
        String sirenInfoByte = statusBytes.get(32);
        SirenInfo sirenInfo = partialStatusInfoParser.sirenInfoParser(sirenIsTurnedOnByte, sirenInfoByte);
        System.out.println("Adicionando Informações da Sirene: " + sirenInfo);

        List<Boolean> communicationProblemByte = partialStatusInfoParser.convertHexByteToBooleanList(statusBytes.get(32));
        Boolean phoneLineIsCut = communicationProblemByte.get(2);
        Boolean failedToCommunicateEvent = communicationProblemByte.get(3);
        System.out.println("Adicionando Informações de Problemas de Comunicação: Corte de linha telefônica="
                + phoneLineIsCut + ", Falha ao comunicar evento=" + failedToCommunicateEvent);

        String pgmInfoByte = statusBytes.get(37);
        List<Pgm> pgms = partialStatusInfoParser.parsePgms(pgmInfoByte);
        System.out.println("Adicionando Pmgs: " + pgms);
        System.out.println("===================================================================================");

        return partialStatusBuilderFactory.build()
                .withZoneInfo(zoneInfo)
                .withCentralModel(centralModel)
                .withFirmwareVersion(firmwareVersion)
                .withPartitionStatus(partitionStatus)
                .withPartitions(partitions)
                .withCentralInfo(centralInfo)
                .withDateTime(dateTime)
                .withPowerInfo(powerInfo)
                .withKeyboards(keyboards)
                .withReceivers(receivers)
                .withBattery(battery)
                .withSirenInfo(sirenInfo)
                .withPhoneLineIsCut(phoneLineIsCut)
                .withFailedToCommunicateEvent(failedToCommunicateEvent)
                .withPgms(pgms)
                .build();
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        List<String> bytes = splitBytes(userInputRawString);
        boolean packageSizeIsValid = (bytes.size() - 2) == Integer.parseInt(bytes.get(0), 16);
        if (!packageSizeIsValid) {
            return false;
        }

        boolean isCommand = PackageIdentifier.getByValue(bytes.get(1)) == PackageIdentifier.COMMAND;
        if (!isCommand) {
            return false;
        }

        boolean isPartialStatus = (bytes.size() - 2) == 44;
        if (!isPartialStatus) {
            return false;
        }

        boolean checksumIsValid = checksumValidator.checksumIsValid(userInputRawString);
        if (!checksumIsValid) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }
}
