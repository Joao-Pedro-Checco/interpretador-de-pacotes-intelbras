package br.com.fulltime.fullarm.modelo.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;

import java.util.List;

public class Bypass extends SubComando {
    private List<Integer> zonasAnuladas;

    public Bypass(List<Integer> zonasAnuladas) {
        super(TipoComando.BYPASS);
        this.zonasAnuladas = zonasAnuladas;
    }

    @Override
    public String toString() {
        return "Bypass{" +
                "zonasAnuladas=" + zonasAnuladas +
                '}';
    }
}
