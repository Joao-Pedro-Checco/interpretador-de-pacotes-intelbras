package br.com.fulltime.fullarm.infra.processor.command.subcommand.bypass;

import br.com.fulltime.fullarm.core.message.command.subcommand.Bypass;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import br.com.fulltime.fullarm.infra.processor.ZoneManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BypassProcessorImpl implements BypassProcessor {
    private final ZoneManager zoneManager;

    public BypassProcessorImpl(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @Override
    public Subcommand process(String argument) {
        List<String> bytes = splitBytes(argument);
        System.out.println("Processando subcomando de Bypass...");
        List<String> zoneBytes = bytes.subList(1, bytes.size());
        List<Integer> annulledZones = zoneManager.parseZonesWithBitOn1(zoneBytes);
        System.out.println("Adicionando zonas anuladas: " + annulledZones);
        Bypass bypass = new Bypass();
        bypass.setAnnulledZones(annulledZones);
        return bypass;
    }

    @Override
    public boolean canProcess(String arguments) {
        List<String> bytes = splitBytes(arguments);
        boolean identifierIsValid = SubcommandIdentifier.getByValue(bytes.get(0)) == SubcommandIdentifier.BYPASS;
        if (!identifierIsValid) {
            return false;
        }

        List<String> zones = bytes.subList(1, bytes.size());
        boolean hasValidZones = zones.isEmpty() || zones.size() <= 8;
        if (!hasValidZones) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String arguments) {
        return Arrays.asList(arguments.split(" "));
    }
}
