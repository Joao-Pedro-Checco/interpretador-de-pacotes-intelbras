package br.com.fulltime.fullarm.core.message.handler;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.handler.ack.AckMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.authentication.AuthenticationMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.CommandMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.event.EventMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.keepalive.KeepAliveMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.nack.NackMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.status.full.FullStatusMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.status.partial.PartialStatusMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.unknown.UnknownPackageMessageHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PackageMessageHandlerFactory {
    private final AckMessageHandler ackMessageHandler;
    private final NackMessageHandler nackMessageHandler;
    private final AuthenticationMessageHandler authenticationMessageHandler;
    private final CommandMessageHandler commandMessageHandler;
    private final EventMessageHandler eventMessageHandler;
    private final KeepAliveMessageHandler keepAliveMessageHandler;
    private final FullStatusMessageHandler fullStatusMessageHandler;
    private final PartialStatusMessageHandler partialStatusMessageHandler;
    private final UnknownPackageMessageHandler unknownPackageMessageHandler;
    private final HashMap<MessageType, PackageMessageHandler> handlerHashMap = new HashMap<>();

    public PackageMessageHandlerFactory(AckMessageHandler ackMessageHandler,
                                        NackMessageHandler nackMessageHandler,
                                        AuthenticationMessageHandler authenticationMessageHandler,
                                        CommandMessageHandler commandMessageHandler,
                                        EventMessageHandler eventMessageHandler,
                                        KeepAliveMessageHandler keepAliveMessageHandler,
                                        FullStatusMessageHandler fullStatusMessageHandler,
                                        PartialStatusMessageHandler partialStatusMessageHandler,
                                        UnknownPackageMessageHandler unknownPackageMessageHandler) {
        this.ackMessageHandler = ackMessageHandler;
        this.nackMessageHandler = nackMessageHandler;
        this.authenticationMessageHandler = authenticationMessageHandler;
        this.commandMessageHandler = commandMessageHandler;
        this.eventMessageHandler = eventMessageHandler;
        this.keepAliveMessageHandler = keepAliveMessageHandler;
        this.fullStatusMessageHandler = fullStatusMessageHandler;
        this.partialStatusMessageHandler = partialStatusMessageHandler;
        this.unknownPackageMessageHandler = unknownPackageMessageHandler;
        initializeHandlerMap();
    }

    private void initializeHandlerMap() {
        handlerHashMap.put(MessageType.ACK, ackMessageHandler);
        handlerHashMap.put(MessageType.NACK, nackMessageHandler);
        handlerHashMap.put(MessageType.AUTHENTICATION, authenticationMessageHandler);
        handlerHashMap.put(MessageType.COMMAND, commandMessageHandler);
        handlerHashMap.put(MessageType.EVENT, eventMessageHandler);
        handlerHashMap.put(MessageType.KEEP_ALIVE, keepAliveMessageHandler);
        handlerHashMap.put(MessageType.FULL_STATUS, fullStatusMessageHandler);
        handlerHashMap.put(MessageType.PARTIAL_STATUS, partialStatusMessageHandler);
    }

    public PackageMessageHandler getHandler(MessageType messageType) {
        return handlerHashMap.getOrDefault(messageType, unknownPackageMessageHandler);
    }
}
