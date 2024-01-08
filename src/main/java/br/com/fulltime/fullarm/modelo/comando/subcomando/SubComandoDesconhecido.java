package br.com.fulltime.fullarm.modelo.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;

public class SubComandoDesconhecido extends SubComando {
    public SubComandoDesconhecido() {
        super(TipoComando.UNKNOWN);
    }
}
