package br.com.fulltime.fullarm.infra.processor.command.subcommand.unknown;

import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.core.message.command.subcommand.UnknownSubcommand;
import org.springframework.stereotype.Service;

@Service
public class UnknownSubcommandProcessorImpl implements UnknownSubcommandProcessor {
    @Override
    public Subcommand process(String argument) {
        return new UnknownSubcommand();
    }

    @Override
    public boolean canProcess(String arguments) {
        return true;
    }
}
