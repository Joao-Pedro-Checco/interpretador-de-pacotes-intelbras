package br.com.fulltime.fullarm.core.message.handler.command.subcommand.sirenon;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class SirenOnMessageHandlerImpl implements SirenOnMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        return subcommand.getType().toString();
    }
}
