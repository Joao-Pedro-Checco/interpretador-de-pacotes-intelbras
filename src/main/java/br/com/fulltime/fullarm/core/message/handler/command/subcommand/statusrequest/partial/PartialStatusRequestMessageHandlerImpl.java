package br.com.fulltime.fullarm.core.message.handler.command.subcommand.statusrequest.partial;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class PartialStatusRequestMessageHandlerImpl implements PartialStatusRequestMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        return subcommand.getType().toString();
    }
}
