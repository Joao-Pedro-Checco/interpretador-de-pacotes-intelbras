package br.com.fulltime.fullarm.core.constants;

public enum MessageType {
    ACK("Ack"),
    AUTHENTICATION("Autenticação"),
    EVENT("Evento"),
    KEEP_ALIVE("Keep Alive"),
    NACK("Nack"),
    COMMAND("Comando"),
    PARTIAL_STATUS("Status Parcial"),
    FULL_STATUS("Status Completo"),
    UNKNOWN("Desconhecido");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
