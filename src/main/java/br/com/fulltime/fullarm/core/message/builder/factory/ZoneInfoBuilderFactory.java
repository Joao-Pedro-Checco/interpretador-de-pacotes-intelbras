package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.ZoneInfoBuilder;
import org.springframework.stereotype.Service;

@Service
public class ZoneInfoBuilderFactory {
    public ZoneInfoBuilder build() {
        return new ZoneInfoBuilder();
    }
}
