package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public class PartialStatusRequest extends Subcommand {
    public PartialStatusRequest() {
        super(SubcommandType.PARTIAL_STATUS_REQUEST);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
