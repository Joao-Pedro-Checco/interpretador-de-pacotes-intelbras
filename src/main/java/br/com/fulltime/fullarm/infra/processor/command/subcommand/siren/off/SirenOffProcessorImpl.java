package br.com.fulltime.fullarm.infra.processor.command.subcommand.siren.off;

import br.com.fulltime.fullarm.core.message.command.subcommand.SirenOff;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

@Service
public class SirenOffProcessorImpl implements SirenOffProcessor {
    @Override
    public Subcommand process(String argument) {
        System.out.println("Processando subcomando de Desligar Sirene...");
        return new SirenOff();
    }

    @Override
    public boolean canProcess(String arguments) {
        boolean identifierIsValid = SubcommandIdentifier.getByValue(arguments) == SubcommandIdentifier.TURN_SIREN_OFF;
        if (!identifierIsValid) {
            return false;
        }

        return true;
    }
}
