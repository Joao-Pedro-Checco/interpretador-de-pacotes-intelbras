package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.command.CommandBuilder;
import org.springframework.stereotype.Service;

@Service
public class CommandBuilderFactory {
    public CommandBuilder build() {
        return new CommandBuilder();
    }
}
