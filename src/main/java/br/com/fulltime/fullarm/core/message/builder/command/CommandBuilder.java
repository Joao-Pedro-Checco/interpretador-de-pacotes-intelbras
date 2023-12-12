package br.com.fulltime.fullarm.core.message.builder.command;

import br.com.fulltime.fullarm.core.message.command.CommandMessage;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;

public class CommandBuilder {
    private final CommandMessage instance;

    public CommandBuilder() {
        this.instance = new CommandMessage();
    }

    public CommandBuilder withPassword(String password) {
        this.instance.setPassword(password);
        return this;
    }

    public CommandBuilder withSubcommand(Subcommand subcommand) {
        this.instance.setSubcommand(subcommand);
        return this;
    }

    public CommandMessage build() {
        return instance;
    }
}
