package br.com.fulltime.fullarm.core.message.ack;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

public class AckMessage extends GenericPackageMessage {
    public AckMessage() {
        super(MessageType.ACK);
    }

    @Override
    public String toString() {
        return type + " -> Comando recebido com sucesso";
    }
}
