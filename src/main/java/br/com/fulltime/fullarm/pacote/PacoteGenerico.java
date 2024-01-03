package br.com.fulltime.fullarm.pacote;

public abstract class PacoteGenerico {
    protected TipoPacote tipo;

    public PacoteGenerico(TipoPacote tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pacote[Tipo: " + this.tipo + "]";
    }
}
