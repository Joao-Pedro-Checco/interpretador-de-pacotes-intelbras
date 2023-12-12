package br.com.fulltime.fullarm.infra.processor.status.full;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.builder.factory.FullStatusBuilderFactory;
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
public class FullStatusProcessorImpl implements FullStatusProcessor {
    private final FullStatusInfoParser fullStatusInfoParser;
    private final FullStatusBuilderFactory fullStatusBuilderFactory;
    private final ChecksumValidator checksumValidator;

    public FullStatusProcessorImpl(FullStatusInfoParser fullStatusInfoParser,
                                   FullStatusBuilderFactory fullStatusBuilderFactory,
                                   ChecksumValidator checksumValidator) {
        this.fullStatusInfoParser = fullStatusInfoParser;
        this.fullStatusBuilderFactory = fullStatusBuilderFactory;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        List<String> bytes = splitBytes(userInputRawContent);
        System.out.println("Montando Status Completo...");
        System.out.println("===================================================================================");
        List<String> bytesStatus = bytes.subList(2, bytes.size() - 1);
        List<String> bytesZonasAbertas = bytesStatus.subList(0, 8);
        List<String> bytesZonasVioladas = bytesStatus.subList(8, 16);
        List<String> bytesZonasAnuladas = bytesStatus.subList(16, 24);
        List<String> bytesZonasComTamper = bytesStatus.subList(43, 44);
        List<String> bytesZonasComCurtoCircuito = bytesStatus.subList(44, 45);
        List<String> bytesZonasStatusBateria = bytesStatus.subList(46, 52);
        ZoneInfo zoneInfo = fullStatusInfoParser.parseZoneInfo(bytesZonasAbertas, bytesZonasVioladas,
                        bytesZonasAnuladas, bytesZonasComTamper, bytesZonasComCurtoCircuito, bytesZonasStatusBateria);
        System.out.println("Adicionando Informações das zonas: " + zoneInfo);

        CentralModel modeloDaCentral = fullStatusInfoParser.parseCentralModel(bytesStatus.get(24));
        System.out.println("Adicionando Modelo da Central: " + modeloDaCentral);

        String versaoFirmware = fullStatusInfoParser.parseFirmwareVersion(bytesStatus.get(25));
        System.out.println("Adicionando Versão do Firmware: " + versaoFirmware);

        PartitionStatus partitionStatus = fullStatusInfoParser.parsePartitionStatus(bytesStatus.get(26));
        System.out.println("Adicionando Status de Partição: " + partitionStatus);

        String bytesParticoes = String.join("", bytesStatus.subList(27, 29));
        List<Partition> particoes = fullStatusInfoParser.parsePartitions(bytesParticoes);
        System.out.println("Adicionando Partições: " + particoes);

        CentralInfo centralInfo = fullStatusInfoParser.parseCentralInfo(bytesStatus.get(29));
        System.out.println("Adicionando Status da Central: " + centralInfo);

        LocalDateTime dataHora = fullStatusInfoParser.parseDateTime(bytesStatus.subList(30, 35));
        System.out.println("Adicionando Data e Hora: " + dataHora);

        PowerInfo powerInfo = fullStatusInfoParser.parsePowerInfo(bytesStatus.get(35));
        System.out.println("Adicionando Informações de Energia: " + powerInfo);

        String byteProblemaTeclados = bytesStatus.get(36);
        String byteTamperTeclados = bytesStatus.get(41);
        List<Keyboard> keyboards = fullStatusInfoParser.parseKeyboards(byteProblemaTeclados, byteTamperTeclados);
        System.out.println("Adicionando Teclados: " + keyboards);

        String byteProblemaReceptores = bytesStatus.get(36);
        List<Receiver> receptores = fullStatusInfoParser.parseReceivers(byteProblemaReceptores);
        System.out.println("Adicionando Receptores: " + receptores);

        Battery battery = fullStatusInfoParser.parseBattery(bytesStatus.get(40));
        System.out.println("Adicionando Informações da Bateria: " + battery);

        List<Expander> expansoresPgm = fullStatusInfoParser.parsePgmExpanders(bytesStatus.get(37));
        System.out.println("Adicionando Expansores de Pgm" + expansoresPgm);

        String nibbleExpansoresByte1 = String.valueOf(bytesStatus.get(37).charAt(1));
        String nibbleExpansoresByte2 = String.valueOf(bytesStatus.get(38).charAt(0));
        List<Expander> expansoresZonas =
                fullStatusInfoParser.parseZoneExpanders(nibbleExpansoresByte1, nibbleExpansoresByte2);
        System.out.println("Adicionando Expansores de Zona: " + expansoresZonas);

        String byteSireneLigada = bytesStatus.get(45);
        String byteInfoSirene = bytesStatus.get(42);
        SirenInfo sirenInfo = fullStatusInfoParser.parseSirenInfo(byteSireneLigada, byteInfoSirene);
        System.out.println("Adicionando Informações da Sirene: " + sirenInfo);

        List<Boolean> problemasComunicacao = fullStatusInfoParser.convertHexByteToBooleanList(bytesStatus.get(42));
        Boolean corteDeLinhaTelefonica = problemasComunicacao.get(2);
        Boolean falhaAoComunicarEvento = problemasComunicacao.get(3);
        System.out.println("Adicionando Informações de Problemas de Comunicação: Corte de linha telefônica=" +
                problemasComunicacao.get(2) + ", Falha ao comunicar evento=" + problemasComunicacao.get(3));

        String bytePgm1 = bytesStatus.get(45);
        String bytesPgm2 = String.join("", bytesStatus.subList(52, 54));
        List<Pgm> pgms = fullStatusInfoParser.parsePgms(bytePgm1, bytesPgm2);
        System.out.println("Adicionando Pgms: " + pgms);
        System.out.println("===================================================================================");

        return fullStatusBuilderFactory.build()
                .withZoneInfo(zoneInfo)
                .withCentralModel(modeloDaCentral)
                .withFirmwareVersion(versaoFirmware)
                .withPartitionStatus(partitionStatus)
                .withPartitions(particoes)
                .withCentralInfo(centralInfo)
                .withDateTime(dataHora)
                .withPowerInfo(powerInfo)
                .withKeyboards(keyboards)
                .withReceivers(receptores)
                .withBattery(battery)
                .withPgmExpanders(expansoresPgm)
                .withZoneExpanders(expansoresZonas)
                .withSirenInfo(sirenInfo)
                .withPhoneLineIsCut(corteDeLinhaTelefonica)
                .withFailedToCommunicateEvent(falhaAoComunicarEvento)
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

        boolean isFullStatus = (bytes.size() - 2) == 55;
        if (!isFullStatus) {
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
