package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public class SirenOn extends Subcommand {
    public SirenOn() {
        super(SubcommandType.TURN_SIREN_ON);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
