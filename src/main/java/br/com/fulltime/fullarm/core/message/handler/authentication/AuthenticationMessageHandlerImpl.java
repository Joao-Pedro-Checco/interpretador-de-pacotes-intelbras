package br.com.fulltime.fullarm.core.message.handler.authentication;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.authentication.AuthenticationMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticationMessageHandlerImpl implements AuthenticationMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        List<String> contentList = new ArrayList<>();
        AuthenticationMessage authenticationMessage = (AuthenticationMessage) genericPackageMessage;
        String messageType = authenticationMessage.getType().toString();
        String connectionType = authenticationMessage.getConnectionType().toString();
        String account = authenticationMessage.getAccount();
        String macAddress = authenticationMessage.getMacAddress();
        Collections.addAll(contentList, messageType, connectionType, account, macAddress);
        return contentList;
    }
}
