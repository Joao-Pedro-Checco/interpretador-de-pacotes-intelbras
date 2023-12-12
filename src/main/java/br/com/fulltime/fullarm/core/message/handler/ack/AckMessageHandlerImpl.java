package br.com.fulltime.fullarm.core.message.handler.ack;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AckMessageHandlerImpl implements AckMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        String messageType = genericPackageMessage.getType().toString();
        String description = "Comando recebido com sucesso.";
        return new ArrayList<String>(){{
            add(messageType);
            add(description);
        }};
    }
}
