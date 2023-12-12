package br.com.fulltime.fullarm.core.message.event;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;
import br.com.fulltime.fullarm.infra.constants.Qualifier;

public class EventMessage extends GenericPackageMessage {
    private ConnectionType connectionType;
    private String account;
    private String contactId;
    private Qualifier qualifier;
    private String eventCode;
    private String partition;
    private String argument;

    public EventMessage() {
        super(MessageType.EVENT);
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public String getAccount() {
        return account;
    }

    public String getContactId() {
        return contactId;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getPartition() {
        return partition;
    }

    public String getArgument() {
        return argument;
    }

    public void setTipoConexao(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "Event{" +
                "connectionType=" + connectionType +
                ", account='" + account + '\'' +
                ", contactId='" + contactId + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", partition='" + partition + '\'' +
                ", argument='" + argument + '\'' +
                ", type=" + type +
                '}';
    }
}
