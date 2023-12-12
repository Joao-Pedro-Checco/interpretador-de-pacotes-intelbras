package br.com.fulltime.fullarm.core.message.builder.authentication;

import br.com.fulltime.fullarm.core.message.authentication.AuthenticationMessage;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;

public class AuthenticationBuilder {
    private final AuthenticationMessage instance;

    public AuthenticationBuilder() {
        this.instance = new AuthenticationMessage();
    }

    public AuthenticationBuilder withConnectionType(ConnectionType connectionType) {
        this.instance.setTipoConexao(connectionType);
        return this;
    }

    public AuthenticationBuilder withAccount(String account) {
        this.instance.setAccount(account);
        return this;
    }

    public AuthenticationBuilder withMacAddress(String macAddress) {
        this.instance.setMacAddress(macAddress);
        return this;
    }

    public AuthenticationMessage build() {
        return instance;
    }
}
