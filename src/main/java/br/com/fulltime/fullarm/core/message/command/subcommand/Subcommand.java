package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

public abstract class Subcommand {
    private final SubcommandType type;

    public Subcommand(SubcommandType type) {
        this.type = type;
    }

    public SubcommandType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Subcommand{" +
                "type=" + type +
                '}';
    }
}
