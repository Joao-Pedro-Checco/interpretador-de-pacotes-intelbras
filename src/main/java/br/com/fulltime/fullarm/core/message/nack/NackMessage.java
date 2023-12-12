package br.com.fulltime.fullarm.core.message.nack;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

public class NackMessage extends GenericPackageMessage {
    private String description;

    public NackMessage() {
        super(MessageType.NACK);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return type + " -> " + description;
    }
}
