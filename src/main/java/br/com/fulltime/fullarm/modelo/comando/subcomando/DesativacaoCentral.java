package br.com.fulltime.fullarm.modelo.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;

import java.util.List;

public class DesativacaoCentral extends SubComando {
    private List<Character> particoes;

    public DesativacaoCentral(List<Character> particoes) {
        super(TipoComando.DESATIVACAO_DA_CENTRAL);
        this.particoes = particoes;
    }

    @Override
    public String toString() {
        return "DesativacaoCentral{" +
                "particoes=" + particoes +
                '}';
    }
}
