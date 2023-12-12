package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.status.BatteryBuilder;
import org.springframework.stereotype.Service;

@Service
public class BatteryBuilderFactory {
    public BatteryBuilder build() {
        return new BatteryBuilder();
    }
}
