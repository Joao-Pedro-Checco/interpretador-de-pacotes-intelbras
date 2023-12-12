package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.complete.FullStatusBuilder;
import org.springframework.stereotype.Service;

@Service
public class FullStatusBuilderFactory {
    public FullStatusBuilder build() {
        return new FullStatusBuilder();
    }
}
