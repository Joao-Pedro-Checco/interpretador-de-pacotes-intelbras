package br.com.fulltime.fullarm.core.message.handler.command;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.command.CommandMessage;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.SubcommandHandlerFactory;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.SubcommandMessageHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommandMessageHandlerImpl implements CommandMessageHandler {
    private final SubcommandHandlerFactory subcommandHandlerFactory;

    public CommandMessageHandlerImpl(SubcommandHandlerFactory subcommandHandlerFactory) {
        this.subcommandHandlerFactory = subcommandHandlerFactory;
    }

    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        List<String> contentList = new ArrayList<>();
        CommandMessage commandMessage = (CommandMessage) genericPackageMessage;
        String messageType = commandMessage.getType().toString();
        String password = commandMessage.getPassword();

        SubcommandType subcommandType = commandMessage.getSubcommand().getType();
        SubcommandMessageHandler subcommandMessageHandler = subcommandHandlerFactory.getHandler(subcommandType);
        String subcommand = subcommandMessageHandler.subcommandToMessage(commandMessage.getSubcommand());

        Collections.addAll(contentList, messageType, password, subcommand);
        return contentList;
    }
}
