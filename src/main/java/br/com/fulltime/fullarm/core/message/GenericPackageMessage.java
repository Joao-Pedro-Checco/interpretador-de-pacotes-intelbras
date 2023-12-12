package br.com.fulltime.fullarm.core.message;

import br.com.fulltime.fullarm.core.constants.MessageType;

public abstract class GenericPackageMessage {
    protected MessageType type;

    public GenericPackageMessage(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Package[Type: " + type + "]";
    }
}
