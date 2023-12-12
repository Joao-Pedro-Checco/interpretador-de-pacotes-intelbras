package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public class SirenOff extends Subcommand {
    public SirenOff() {
        super(SubcommandType.TURN_SIREN_OFF);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
