package br.com.fulltime.fullarm.core.message.handler.command.subcommand.centralactivation;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.command.subcommand.CentralActivation;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class CentralActivationMessageHandlerImpl implements CentralActivationMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        CentralActivation centralActivation = (CentralActivation) subcommand;
        SubcommandType subcommandType = centralActivation.getType();
        return subcommandType + " -> Partições: " + centralActivation.getPartitions();
    }
}
