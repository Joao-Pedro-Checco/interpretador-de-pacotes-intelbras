package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.partial.PartialStatusBuilder;
import org.springframework.stereotype.Service;

@Service
public class PartialStatusBuilderFactory {
    public PartialStatusBuilder build() {
        return new PartialStatusBuilder();
    }
}
