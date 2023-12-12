package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public class UnknownSubcommand extends Subcommand {
    public UnknownSubcommand() {
        super(SubcommandType.UNKNOWN);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
