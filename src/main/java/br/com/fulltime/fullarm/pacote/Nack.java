package br.com.fulltime.fullarm.pacote;

public class Nack extends Pacote {
    private final String descricao;

    public Nack(TipoPacote tipo, String descricao) {
        super(tipo);
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.tipo + " -> " + this.descricao;
    }
}
