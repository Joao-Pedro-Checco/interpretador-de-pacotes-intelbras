package br.com.fulltime.fullarm.core.message.keepalive;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

public class KeepAliveMessage extends GenericPackageMessage {
    public KeepAliveMessage() {
        super(MessageType.KEEP_ALIVE);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
