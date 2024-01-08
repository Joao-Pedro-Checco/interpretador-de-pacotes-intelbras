package br.com.fulltime.fullarm.modelo;

import br.com.fulltime.fullarm.constantes.TipoProcessador;

public class Nack extends PacoteGenerico {
    private final String descricao;

    public Nack(String descricao) {
        super(TipoProcessador.NACK);
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.tipo + " -> " + this.descricao;
    }
}
