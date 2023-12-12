package br.com.fulltime.fullarm.core.message.handler.command.subcommand.statusrequest.full;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class FullStatusRequestMessageHandlerImpl implements FullStatusRequestMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        return subcommand.getType().toString();
    }
}
