package br.com.fulltime.fullarm.infra.processor.command.subcommand.panic;

import br.com.fulltime.fullarm.core.message.command.subcommand.Panic;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.PanicType;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PanicProcessorImpl implements PanicProcessor {
    private static final List<String> validPanicTypes = new ArrayList<String>(){{
        add("00");
        add("01");
        add("02");
        add("03");
    }};

    @Override
    public Subcommand process(String argument) {
        List<String> bytes = splitBytes(argument);
        System.out.println("Processando subcomando de Pânico...");
        PanicType panicType = PanicType.getByValue(bytes.get(1));
        System.out.println("Adicionando tipo de pânico: " + panicType);
        Panic panic = new Panic();
        panic.setPanicType(panicType);
        return panic;
    }

    @Override
    public boolean canProcess(String arguments) {
        List<String> bytes = splitBytes(arguments);
        boolean identifierIsValid = SubcommandIdentifier.getByValue(bytes.get(0)) == SubcommandIdentifier.PANIC;
        if (!identifierIsValid) {
            return false;
        }

        boolean panicTypeIsValid = validPanicTypes.contains(bytes.get(1));
        if (!panicTypeIsValid) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String arguments) {
        return Arrays.asList(arguments.split(" "));
    }
}
