package br.com.fulltime.fullarm.core.message.authentication;

import br.com.fulltime.fullarm.core.constants.MessageType;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.infra.constants.ConnectionType;

public class AuthenticationMessage extends GenericPackageMessage {
    private ConnectionType connectionType;
    private String account;
    private String macAddress;

    public AuthenticationMessage() {
        super(MessageType.AUTHENTICATION);
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public String getAccount() {
        return account;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setTipoConexao(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "connectionType=" + connectionType +
                ", account='" + account + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", type=" + type +
                '}';
    }
}
