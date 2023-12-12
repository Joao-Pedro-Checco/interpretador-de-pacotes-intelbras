package br.com.fulltime.fullarm.pacote;

public class KeepAlive extends Pacote {
    public KeepAlive(TipoPacote tipo) {
        super(tipo);
    }

    @Override
    public String toString() {
        return this.tipo + "";
    }
}
