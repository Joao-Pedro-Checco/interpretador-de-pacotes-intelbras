package br.com.fulltime.fullarm.modelo.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoComando;

import java.util.List;

public class AtivacaoCentral extends SubComando {
    private List<Character> particoes;
    public AtivacaoCentral(List<Character> particoes) {
        super(TipoComando.ATIVACAO_DA_CENTRAL);
        this.particoes = particoes;
    }

    @Override
    public String toString() {
        return "AtivacaoCentral{" +
                "particoes=" + particoes +
                '}';
    }
}
