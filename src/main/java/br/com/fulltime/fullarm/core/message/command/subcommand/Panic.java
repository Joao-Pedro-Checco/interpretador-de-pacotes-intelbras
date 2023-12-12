package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.infra.constants.PanicType;

public class Panic extends Subcommand {
    private PanicType panicType;

    public Panic() {
        super(SubcommandType.PANIC);
    }

    public PanicType getPanicType() {
        return panicType;
    }

    public void setPanicType(PanicType panicType) {
        this.panicType = panicType;
    }

    @Override
    public String toString() {
        return "Panic{" +
                "panicType=" + panicType +
                '}';
    }
}
