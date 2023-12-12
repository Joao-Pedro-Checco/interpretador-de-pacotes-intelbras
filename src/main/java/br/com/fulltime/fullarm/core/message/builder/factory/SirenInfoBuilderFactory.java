package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.SirenInfoBuilder;
import org.springframework.stereotype.Service;

@Service
public class SirenInfoBuilderFactory {
    public SirenInfoBuilder build() {
        return new SirenInfoBuilder();
    }
}
