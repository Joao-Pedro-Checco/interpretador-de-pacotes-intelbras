package br.com.fulltime.fullarm.infra.processor.command.subcommand.siren.on;

import br.com.fulltime.fullarm.core.message.command.subcommand.SirenOn;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

@Service
public class SirenOnProcessorImpl implements SirenOnProcessor {
    @Override
    public Subcommand process(String argument) {
        System.out.println("Processando subcomando de Ligar Sirene...");
        return new SirenOn();
    }

    @Override
    public boolean canProcess(String arguments) {
        boolean identifierIsValid = SubcommandIdentifier.getByValue(arguments) == SubcommandIdentifier.TURN_SIREN_ON;
        if (!identifierIsValid) {
            return false;
        }

        return true;
    }
}
