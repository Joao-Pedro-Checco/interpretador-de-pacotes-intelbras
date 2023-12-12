package br.com.fulltime.fullarm.infra.processor.command.subcommand.pgmcontrol;

import br.com.fulltime.fullarm.core.message.command.subcommand.PgmControl;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.PgmAction;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PgmControlProcessorImpl implements PgmControlProcessor {
    @Override
    public Subcommand process(String argument) {
        List<String> subcommandBytes = splitBytes(argument);
        System.out.println("Processando subcomando Controle de PGM...");
        PgmAction pgmAction = PgmAction.getByValue(subcommandBytes.get(1));
        System.out.println("Adicionando Ação da Pgm: " + pgmAction);
        List<Integer> pgms = parsePgms(subcommandBytes);
        PgmControl pgmControl = new PgmControl();
        pgmControl.setPgmAction(pgmAction);
        pgmControl.setPgms(pgms);
        return pgmControl;
    }

    @Override
    public boolean canProcess(String arguments) {
        List<String> bytes = splitBytes(arguments);
        boolean identifierIsValid = SubcommandIdentifier.getByValue(bytes.get(0)) == SubcommandIdentifier.PGM_CONTROL;
        if (!identifierIsValid) {
            return false;
        }

        boolean pgmActionIsValid = PgmAction.getByValue(bytes.get(1)) != PgmAction.UNKNOWN;
        if (!pgmActionIsValid) {
            return false;
        }

        int pgmHex = parsePgmHex(bytes.get(2));
        boolean pgmHexIsValid = pgmHex >= 1 && pgmHex <= 19;
        if (!pgmHexIsValid) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String arguments) {
        return Arrays.asList(arguments.split(" "));
    }

    private List<Integer> parsePgms(List<String> bytes) {
        List<String> pgmBytes = bytes.subList(2, bytes.size());
        return pgmBytes.stream().map(this::parsePgmHex).collect(Collectors.toList());
    }

    private int parsePgmHex(String b) {
        return Integer.parseInt(b, 16) - 0x30;
    }
}
