package br.com.fulltime.fullarm.core.message.handler.nack;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.nack.NackMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NackMessageHandlerImpl implements NackMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        String messageType = genericPackageMessage.getType().toString();
        String description = ((NackMessage) genericPackageMessage).getDescription().toString();
        return new ArrayList<String>(){{
            add(messageType);
            add(description);
        }};
    }
}
