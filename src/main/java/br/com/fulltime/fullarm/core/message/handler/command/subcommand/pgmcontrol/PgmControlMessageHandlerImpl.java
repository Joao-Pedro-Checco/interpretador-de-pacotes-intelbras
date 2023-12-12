package br.com.fulltime.fullarm.core.message.handler.command.subcommand.pgmcontrol;

import br.com.fulltime.fullarm.core.message.command.subcommand.PgmControl;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import org.springframework.stereotype.Service;

@Service
public class PgmControlMessageHandlerImpl implements PgmControlMessageHandler {
    @Override
    public String subcommandToMessage(Subcommand subcommand) {
        PgmControl pgmControl = (PgmControl) subcommand;
        return pgmControl.getPgmAction() + " -> Pgms: " + pgmControl.getPgms();
    }
}
