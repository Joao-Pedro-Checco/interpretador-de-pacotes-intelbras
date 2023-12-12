package br.com.fulltime.fullarm.infra.constants;

public enum ConnectionType {
    ETHERNET("45", "Ethernet"),
    GPRS_1("47", "GPRS 1"),
    GPRS_2("48", "GPRS 2"),
    ETHERNET_IP_1("11", "Ethernet IP 1"),
    ETHERNET_IP_2("12", "Ethernet IP 2"),
    GPRS_IP_1("21", "GPRS IP 1"),
    GPRS_IP_2("22", "GPRS IP 2"),
    UNKNOWN("", "Desconhecido");

    private final String connectionByte;
    private final String description;

    ConnectionType(String connectionByte, String description) {
        this.connectionByte = connectionByte;
        this.description = description;
    }

    public String getConnectionByte() {
        return connectionByte;
    }

    public static ConnectionType getByValue(String connectionByte) {
        for (ConnectionType ct : values()) {
            if (connectionByte.equals(ct.getConnectionByte())) return ct;
        }

        return ConnectionType.UNKNOWN;
    }

    @Override
    public String toString() {
        return description;
    }
}
