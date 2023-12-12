package br.com.fulltime.fullarm.pacote;

public abstract class Pacote {
    protected TipoPacote tipo;

    public Pacote(TipoPacote tipo) {
        this.tipo = tipo;
    }
}
