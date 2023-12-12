package br.com.fulltime.fullarm.core.message.handler.command.subcommand.panic;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.command.subcommand.Panic;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class PanicMessageHandlerImpl implements PanicMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        Panic panic = (Panic) subcommand;
        SubcommandType subcommandType = panic.getType();
        return subcommandType + " -> " + panic.getPanicType();
    }
}
