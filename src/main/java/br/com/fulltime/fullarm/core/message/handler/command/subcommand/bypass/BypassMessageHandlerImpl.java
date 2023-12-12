package br.com.fulltime.fullarm.core.message.handler.command.subcommand.bypass;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.command.subcommand.Bypass;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class BypassMessageHandlerImpl implements BypassMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        Bypass bypass = (Bypass) subcommand;
        SubcommandType subcommandType = bypass.getType();
        return subcommandType + " -> Zonas anuladas: " + bypass.getAnnulledZones();
    }
}
