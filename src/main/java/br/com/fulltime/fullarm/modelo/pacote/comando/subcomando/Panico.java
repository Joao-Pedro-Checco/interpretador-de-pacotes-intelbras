package br.com.fulltime.fullarm.modelo.pacote.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;
import br.com.fulltime.fullarm.constantes.TipoPanico;

public class Panico extends SubComando {
    private TipoPanico tipo;

    public Panico(TipoPanico tipo) {
        super(TipoComando.PANICO);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Panico{" +
                "tipo=" + tipo +
                '}';
    }
}
