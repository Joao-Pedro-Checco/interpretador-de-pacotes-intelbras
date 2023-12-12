package br.com.fulltime.fullarm.core.message.unknown;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

public class UnknownPackageMessage extends GenericPackageMessage {
    public UnknownPackageMessage() {
        super(MessageType.UNKNOWN);
    }
}
