package br.com.fulltime.fullarm.core.message.handler.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.bypass.BypassMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.centralactivation.CentralActivationMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.centraldeactivation.CentralDeactivationMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.panic.PanicMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.pgmcontrol.PgmControlMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.sirenoff.SirenOffMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.sirenon.SirenOnMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.statusrequest.full.FullStatusRequestMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.statusrequest.partial.PartialStatusRequestMessageHandler;
import br.com.fulltime.fullarm.core.message.handler.command.subcommand.unknown.UnknownSubcommandMessageHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SubcommandHandlerFactory {
    private final BypassMessageHandler bypassMessageHandler;
    private final CentralActivationMessageHandler centralActivationMessageHandler;
    private final CentralDeactivationMessageHandler centralDeactivationMessageHandler;
    private final PanicMessageHandler panicMessageHandler;
    private final PgmControlMessageHandler pgmControlMessageHandler;
    private final SirenOffMessageHandler sirenOffMessageHandler;
    private final SirenOnMessageHandler sirenOnMessageHandler;
    private final FullStatusRequestMessageHandler fullStatusRequestMessageHandler;
    private final PartialStatusRequestMessageHandler partialStatusRequestMessageHandler;
    private final UnknownSubcommandMessageHandler unknownSubcommandMessageHandler;
    private final HashMap<SubcommandType, SubcommandMessageHandler> subcommandMessageHandlerHashMap = new HashMap<>();

    public SubcommandHandlerFactory(BypassMessageHandler bypassMessageHandler,
                                    CentralActivationMessageHandler centralActivationMessageHandler,
                                    CentralDeactivationMessageHandler centralDeactivationMessageHandler,
                                    PanicMessageHandler panicMessageHandler,
                                    PgmControlMessageHandler pgmControlMessageHandler,
                                    SirenOffMessageHandler sirenOffMessageHandler,
                                    SirenOnMessageHandler sirenOnMessageHandler,
                                    FullStatusRequestMessageHandler fullStatusRequestMessageHandler,
                                    PartialStatusRequestMessageHandler partialStatusRequestMessageHandler,
                                    UnknownSubcommandMessageHandler unknownSubcommandMessageHandler) {
        this.bypassMessageHandler = bypassMessageHandler;
        this.centralActivationMessageHandler = centralActivationMessageHandler;
        this.centralDeactivationMessageHandler = centralDeactivationMessageHandler;
        this.panicMessageHandler = panicMessageHandler;
        this.pgmControlMessageHandler = pgmControlMessageHandler;
        this.sirenOffMessageHandler = sirenOffMessageHandler;
        this.sirenOnMessageHandler = sirenOnMessageHandler;
        this.fullStatusRequestMessageHandler = fullStatusRequestMessageHandler;
        this.partialStatusRequestMessageHandler = partialStatusRequestMessageHandler;
        this.unknownSubcommandMessageHandler = unknownSubcommandMessageHandler;
        initializeHashMap();
    }

    private void initializeHashMap() {
        subcommandMessageHandlerHashMap.put(SubcommandType.BYPASS, bypassMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.CENTRAL_ACTIVATION, centralActivationMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.CENTRAL_DEACTIVATION, centralDeactivationMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.PANIC, panicMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.PGM_CONTROL, pgmControlMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.TURN_SIREN_OFF, sirenOffMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.TURN_SIREN_ON, sirenOnMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.FULL_STATUS_REQUEST, fullStatusRequestMessageHandler);
        subcommandMessageHandlerHashMap.put(SubcommandType.PARTIAL_STATUS_REQUEST, partialStatusRequestMessageHandler);
    }

    public SubcommandMessageHandler getHandler(SubcommandType subcommandType) {
        return subcommandMessageHandlerHashMap.getOrDefault(subcommandType, unknownSubcommandMessageHandler);
    }
}
