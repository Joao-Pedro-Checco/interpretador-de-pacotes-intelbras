package br.com.fulltime.fullarm.core.message.handler.command.subcommand;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;

public interface SubcommandMessageHandler {
    String subcommandToMessage(Subcommand subcommand);
}
