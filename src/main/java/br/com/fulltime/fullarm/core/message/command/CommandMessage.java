package br.com.fulltime.fullarm.core.message.command;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;

public class CommandMessage extends GenericPackageMessage {
    private String password;
    private Subcommand subcommand;

    public CommandMessage() {
        super(MessageType.COMMAND);
    }

    public String getPassword() {
        return password;
    }

    public Subcommand getSubcommand() {
        return subcommand;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubcommand(Subcommand subcommand) {
        this.subcommand = subcommand;
    }

    @Override
    public String toString() {
        return "Command{" +
                "password='" + password + '\'' +
                ", subcommand=" + subcommand +
                ", type=" + type +
                '}';
    }
}
