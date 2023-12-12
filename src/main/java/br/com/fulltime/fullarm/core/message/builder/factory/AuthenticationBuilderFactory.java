package br.com.fulltime.fullarm.core.message.builder.factory;

import br.com.fulltime.fullarm.core.message.builder.authentication.AuthenticationBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationBuilderFactory {
    public AuthenticationBuilder build() {
        return new AuthenticationBuilder();
    }
}
