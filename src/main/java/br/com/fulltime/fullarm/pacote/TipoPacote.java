package br.com.fulltime.fullarm.pacote;

public enum TipoPacote {
    ACK("Ack"),
    AUTENTICACAO("Autenticação"),
    EVENTO("Evento"),
    KEEP_ALIVE("Keep Alive"),
    NACK("Nack"),
    COMANDO("Comando"),
    STATUS_PARCIAL("Status Parcial"),
    STATUS_COMPLETO("Status Completo"),
    UNKNOWN("Desconhecido");

    private final String descricao;

    TipoPacote(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
