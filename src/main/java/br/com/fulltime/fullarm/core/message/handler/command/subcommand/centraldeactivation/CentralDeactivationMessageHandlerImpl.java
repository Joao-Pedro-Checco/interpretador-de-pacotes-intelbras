package br.com.fulltime.fullarm.core.message.handler.command.subcommand.centraldeactivation;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.command.subcommand.CentralDeactivation;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class CentralDeactivationMessageHandlerImpl implements CentralDeactivationMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        CentralDeactivation centralDeactivation = (CentralDeactivation) subcommand;
        SubcommandType subcommandType = centralDeactivation.getType();
        return subcommandType + " -> Partições: " + centralDeactivation.getPartitions();
    }
}
