package br.com.fulltime.fullarm.core.message.handler.command.subcommand.unknown;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class UnknownSubcommandMessageHandlerImpl implements UnknownSubcommandMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        return subcommand.getType().toString();
    }
}
