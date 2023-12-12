package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.CentralInfoBuilder;
import org.springframework.stereotype.Service;

@Service
public class CentralInfoBuilderFactory {
    public CentralInfoBuilder build() {
        return new CentralInfoBuilder();
    }
}
