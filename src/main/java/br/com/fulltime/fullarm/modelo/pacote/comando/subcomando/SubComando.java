package br.com.fulltime.fullarm.modelo.pacote.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;

public abstract class SubComando {
    private TipoComando identificador;

    public SubComando(TipoComando identificador) {
        this.identificador = identificador;
    }
}
