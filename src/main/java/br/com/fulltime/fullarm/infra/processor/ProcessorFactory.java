package br.com.fulltime.fullarm.infra.processor;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.infra.processor.ack.AckProcessor;
import br.com.fulltime.fullarm.infra.processor.authentication.AuthenticationProcessor;
import br.com.fulltime.fullarm.infra.processor.command.CommandProcessor;
import br.com.fulltime.fullarm.infra.processor.event.EventProcessor;
import br.com.fulltime.fullarm.infra.processor.keepalive.KeepAliveProcessor;
import br.com.fulltime.fullarm.infra.processor.nack.NackProcessor;
import br.com.fulltime.fullarm.infra.processor.status.full.FullStatusProcessor;
import br.com.fulltime.fullarm.infra.processor.status.partial.PartialStatusProcessor;
import br.com.fulltime.fullarm.infra.processor.unknown.UnknownPackageProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorFactory {
    private final AckProcessor ackProcessorImpl;
    private final NackProcessor nackProcessorImpl;
    private final AuthenticationProcessor authenticationProcessorImpl;
    private final EventProcessor eventProcessorImpl;
    private final KeepAliveProcessor keepAliveProcessorImpl;
    private final CommandProcessor commandProcessorImpl;
    private final PartialStatusProcessor partialStatusProcessorImpl;
    private final FullStatusProcessor fullStatusProcessorImpl;
    private final UnknownPackageProcessor unknownPackageProcessorImpl;
    private final List<PackageProcessor> processorList = new ArrayList<>();

    public ProcessorFactory(AckProcessor ackProcessorImpl,
                            NackProcessor nackProcessorImpl,
                            AuthenticationProcessor authenticationProcessorImpl,
                            EventProcessor eventProcessorImpl,
                            KeepAliveProcessor keepAliveProcessorImpl,
                            CommandProcessor commandProcessorImpl,
                            PartialStatusProcessor partialStatusProcessorImpl,
                            FullStatusProcessor fullStatusProcessorImpl,
                            UnknownPackageProcessor unknownPackageProcessorImpl) {
        this.ackProcessorImpl = ackProcessorImpl;
        this.nackProcessorImpl = nackProcessorImpl;
        this.authenticationProcessorImpl = authenticationProcessorImpl;
        this.eventProcessorImpl = eventProcessorImpl;
        this.keepAliveProcessorImpl = keepAliveProcessorImpl;
        this.commandProcessorImpl = commandProcessorImpl;
        this.partialStatusProcessorImpl = partialStatusProcessorImpl;
        this.fullStatusProcessorImpl = fullStatusProcessorImpl;
        this.unknownPackageProcessorImpl = unknownPackageProcessorImpl;
        initializeList();
    }

    private void initializeList() {
        processorList.add(keepAliveProcessorImpl);
        processorList.add(eventProcessorImpl);
        processorList.add(ackProcessorImpl);
        processorList.add(authenticationProcessorImpl);
        processorList.add(nackProcessorImpl);
        processorList.add(commandProcessorImpl);
        processorList.add(partialStatusProcessorImpl);
        processorList.add(fullStatusProcessorImpl);
    }

    public PackageProcessor getProcessor(PackageUserInput packageUserInput) {
        return processorList.stream()
                .filter(processor -> processor.canProcess(packageUserInput.getRawContent()))
                .findFirst().orElse(unknownPackageProcessorImpl);
    }
}
