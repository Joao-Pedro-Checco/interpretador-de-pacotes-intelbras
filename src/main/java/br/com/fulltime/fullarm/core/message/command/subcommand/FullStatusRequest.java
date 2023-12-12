package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public class FullStatusRequest extends Subcommand {
    public FullStatusRequest() {
        super(SubcommandType.FULL_STATUS_REQUEST);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
