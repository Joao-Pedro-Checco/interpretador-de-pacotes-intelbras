package br.com.fulltime.fullarm.constantes;

public enum TipoProcessador {
    ACK("Ack"),
    AUTENTICACAO("Autenticação"),
    EVENTO("Evento"),
    KEEP_ALIVE("Keep Alive"),
    NACK("Nack"),
    COMANDO("Comando"),
    STATUS_PARCIAL("Status Parcial"),
    STATUS_COMPLETO("Status Completo");

    private final String descricao;

    TipoProcessador(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
