package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.event.EventBuilder;
import org.springframework.stereotype.Service;

@Service
public class EventBuilderFactory {
    public EventBuilder build() {
        return new EventBuilder();
    }
}
