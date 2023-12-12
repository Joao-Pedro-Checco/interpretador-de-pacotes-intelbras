package br.com.fulltime.fullarm.core.message.handler;

import br.com.fulltime.fullarm.core.PackageInterpreter;
import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageHandler {
    private final PackageMessageHandlerFactory packageMessageHandlerFactory;
    private final PackageInterpreter packageInterpreter;

    public MessageHandler(PackageMessageHandlerFactory packageMessageHandlerFactory, PackageInterpreter packageInterpreter) {
        this.packageMessageHandlerFactory = packageMessageHandlerFactory;
        this.packageInterpreter = packageInterpreter;
    }

    public List<String> packageToMessageList(PackageUserInput packageUserInput) {
        GenericPackageMessage genericPackageMessage = packageInterpreter.interpretPackage(packageUserInput);
        MessageType messageType = genericPackageMessage.getType();
        PackageMessageHandler packageMessageHandler = packageMessageHandlerFactory.getHandler(messageType);
        return packageMessageHandler.handleMessage(genericPackageMessage);
    }
}
