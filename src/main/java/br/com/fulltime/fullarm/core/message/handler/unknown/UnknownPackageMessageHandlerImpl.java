package br.com.fulltime.fullarm.core.message.handler.unknown;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnknownPackageMessageHandlerImpl implements UnknownPackageMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        String messageType = genericPackageMessage.getType().toString();
        String description = "O pacote digitado é desconhecido";
        return new ArrayList<String>(){{
            add(messageType);
            add(description);
        }};
    }
}
