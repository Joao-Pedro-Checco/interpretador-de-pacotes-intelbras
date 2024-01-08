package br.com.fulltime.fullarm.modelo.pacote;

import br.com.fulltime.fullarm.constantes.TipoProcessador;

public class Ack extends PacoteGenerico {
    public Ack() {
        super(TipoProcessador.ACK);
    }

    @Override
    public String toString() {
        return this.tipo + " -> Comando recebido com sucesso";
    }
}
