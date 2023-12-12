package br.com.fulltime.fullarm.infra.processor.command.subcommand;

import br.com.fulltime.fullarm.infra.processor.command.subcommand.bypass.BypassProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.centralactivation.CentralActivationProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.centraldeactivation.CentralDeactivationProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.panic.PanicProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.pgmcontrol.PgmControlProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.siren.off.SirenOffProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.siren.on.SirenOnProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.statusrequest.full.FullStatusRequestProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.statusrequest.partial.PartialStatusRequestProcessor;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.unknown.UnknownSubcommandProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcommandProcessorFactory {
    private final CentralActivationProcessor centralActivationProcessor;
    private final CentralDeactivationProcessor centralDeactivationProcessor;
    private final BypassProcessor bypassProcessor;
    private final PgmControlProcessor pgmControlProcessor;
    private final PanicProcessor panicProcessor;
    private final PartialStatusRequestProcessor partialStatusRequestProcessor;
    private final FullStatusRequestProcessor fullStatusRequestProcessor;
    private final SirenOnProcessor sirenOnProcessor;
    private final SirenOffProcessor sirenOffProcessor;
    private final UnknownSubcommandProcessor unknownSubcommandProcessor;
    private final List<SubcommandProcessor> processorList = new ArrayList<>();

    public SubcommandProcessorFactory(CentralActivationProcessor centralActivationProcessor,
                                      CentralDeactivationProcessor centralDeactivationProcessor,
                                      BypassProcessor bypassProcessor,
                                      PgmControlProcessor pgmControlProcessor,
                                      PanicProcessor panicProcessor,
                                      PartialStatusRequestProcessor partialStatusRequestProcessor,
                                      FullStatusRequestProcessor fullStatusRequestProcessor,
                                      SirenOnProcessor sirenOnProcessor,
                                      SirenOffProcessor sirenOffProcessor,
                                      UnknownSubcommandProcessor unknownSubcommandProcessor) {
        this.centralActivationProcessor = centralActivationProcessor;
        this.centralDeactivationProcessor = centralDeactivationProcessor;
        this.bypassProcessor = bypassProcessor;
        this.pgmControlProcessor = pgmControlProcessor;
        this.panicProcessor = panicProcessor;
        this.partialStatusRequestProcessor = partialStatusRequestProcessor;
        this.fullStatusRequestProcessor = fullStatusRequestProcessor;
        this.sirenOnProcessor = sirenOnProcessor;
        this.sirenOffProcessor = sirenOffProcessor;
        this.unknownSubcommandProcessor = unknownSubcommandProcessor;
        initializeList();
    }

    private void initializeList() {
        processorList.add(centralActivationProcessor);
        processorList.add(centralDeactivationProcessor);
        processorList.add(bypassProcessor);
        processorList.add(pgmControlProcessor);
        processorList.add(panicProcessor);
        processorList.add(partialStatusRequestProcessor);
        processorList.add(fullStatusRequestProcessor);
        processorList.add(sirenOnProcessor);
        processorList.add(sirenOffProcessor);
    }

    public SubcommandProcessor getProcessor(String subcommandBytes) {
        return processorList.stream()
                .filter(processor -> processor.canProcess(subcommandBytes))
                .findFirst().orElse(unknownSubcommandProcessor);
    }
}
