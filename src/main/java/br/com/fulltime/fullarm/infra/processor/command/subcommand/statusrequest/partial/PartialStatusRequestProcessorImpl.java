package br.com.fulltime.fullarm.infra.processor.command.subcommand.statusrequest.partial;

import br.com.fulltime.fullarm.core.message.command.subcommand.PartialStatusRequest;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

@Service
public class PartialStatusRequestProcessorImpl implements PartialStatusRequestProcessor {
    @Override
    public Subcommand process(String argument) {
        System.out.println("Processando subcomando de Solicitação de Status Parcial...");
        return new PartialStatusRequest();
    }

    @Override
    public boolean canProcess(String arguments) {
        boolean identifierIsValid = SubcommandIdentifier.getByValue(arguments) == SubcommandIdentifier.PARTIAL_STATUS_REQUEST;
        if (!identifierIsValid) {
            return false;
        }

        return true;
    }
}
