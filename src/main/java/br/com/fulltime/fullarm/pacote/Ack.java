package br.com.fulltime.fullarm.pacote;

public class Ack extends Pacote {
    public Ack(TipoPacote tipo) {
        super(tipo);
    }

    @Override
    public String toString() {
        return this.tipo + " -> Comando recebido com sucesso";
    }
}
