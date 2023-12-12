package br.com.fulltime.fullarm.infra.processor.command.subcommand;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;

public interface SubcommandProcessor {
    Subcommand process(String argument);

    boolean canProcess(String arguments);
}
