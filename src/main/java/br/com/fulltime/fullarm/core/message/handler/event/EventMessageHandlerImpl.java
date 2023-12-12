package br.com.fulltime.fullarm.core.message.handler.event;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.event.EventMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventMessageHandlerImpl implements EventMessageHandler {
    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        List<String> contentList = new ArrayList<>();
        EventMessage eventMessage = (EventMessage) genericPackageMessage;
        String messageType = eventMessage.getType().toString();
        String connectionType = eventMessage.getConnectionType().toString();
        String account = eventMessage.getAccount();
        String contactId = eventMessage.getContactId();
        String qualifier = eventMessage.getQualifier().toString();
        String eventCode = eventMessage.getEventCode();
        String partition = eventMessage.getPartition();
        String argument = eventMessage.getArgument();
        Collections.addAll(contentList, messageType, connectionType, account, contactId, qualifier, eventCode, partition, argument);
        return contentList;
    }
}
