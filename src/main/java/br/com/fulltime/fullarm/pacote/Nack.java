package br.com.fulltime.fullarm.pacote;

public class Nack extends PacoteGenerico {
    private final String descricao;

    public Nack(String descricao) {
        super(TipoPacote.NACK);
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.tipo + " -> " + this.descricao;
    }
}
