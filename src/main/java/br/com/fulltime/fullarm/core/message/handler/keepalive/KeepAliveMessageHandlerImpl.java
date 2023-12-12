package br.com.fulltime.fullarm.core.message.handler.keepalive;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeepAliveMessageHandlerImpl implements KeepAliveMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        String messageType = genericPackageMessage.getType().toString();
        String description = "Keep-Alive";
        return new ArrayList<String>(){{
           add(messageType);
           add(description);
        }};
    }
}
