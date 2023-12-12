package br.com.fulltime.fullarm.infra.processor.command.subcommand.statusrequest.full;

import br.com.fulltime.fullarm.core.message.command.subcommand.FullStatusRequest;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

@Service
public class FullStatusRequestProcessorImpl implements FullStatusRequestProcessor {
    @Override
    public Subcommand process(String argument) {
        System.out.println("Processando subcomando de Solicitação de Status Completo...");
        return new FullStatusRequest();
    }

    @Override
    public boolean canProcess(String arguments) {
        boolean identifierIsValid = SubcommandIdentifier.getByValue(arguments) == SubcommandIdentifier.FULL_STATUS_REQUEST;
        if (!identifierIsValid) {
            return false;
        }

        return true;
    }
}
