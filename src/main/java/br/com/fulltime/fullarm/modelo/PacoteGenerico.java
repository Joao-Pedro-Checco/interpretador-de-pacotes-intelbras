package br.com.fulltime.fullarm.modelo;

import br.com.fulltime.fullarm.constantes.TipoProcessador;

public abstract class PacoteGenerico {
    protected TipoProcessador tipo;

    public PacoteGenerico(TipoProcessador tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pacote[Tipo: " + this.tipo + "]";
    }
}
