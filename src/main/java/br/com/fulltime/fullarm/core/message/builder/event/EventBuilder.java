package br.com.fulltime.fullarm.core.message.builder.event;

import br.com.fulltime.fullarm.core.message.event.EventMessage;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;
import br.com.fulltime.fullarm.infra.constants.Qualifier;

public class EventBuilder {
    private final EventMessage instance;

    public EventBuilder() {
        this.instance = new EventMessage();
    }

    public EventBuilder withConnectionType(ConnectionType connectionType) {
        this.instance.setTipoConexao(connectionType);
        return this;
    }

    public EventBuilder withAccount(String account) {
        this.instance.setAccount(account);
        return this;
    }

    public EventBuilder withContactId(String contactId) {
        this.instance.setContactId(contactId);
        return this;
    }

    public EventBuilder withQualifier(Qualifier qualifier) {
        this.instance.setQualifier(qualifier);
        return this;
    }

    public EventBuilder withEventCode(String eventCode) {
        this.instance.setEventCode(eventCode);
        return this;
    }

    public EventBuilder withPartition(String partition) {
        this.instance.setPartition(partition);
        return this;
    }

    public EventBuilder withArgument(String argument) {
        this.instance.setArgument(argument);
        return this;
    }

    public EventMessage build() {
        return instance;
    }
}
