package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.PowerInfoBuilder;
import org.springframework.stereotype.Service;

@Service
public class PowerInfoBuilderFactory {
    public PowerInfoBuilder build() {
        return new PowerInfoBuilder();
    }
}
