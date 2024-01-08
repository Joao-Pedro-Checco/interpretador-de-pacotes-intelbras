package br.com.fulltime.fullarm.modelo.comando.subcomando;

import br.com.fulltime.fullarm.constantes.AcaoPgm;
import br.com.fulltime.fullarm.constantes.TipoComando;

import java.util.List;

public class ControlePgm extends SubComando {
    private AcaoPgm acao;
    private List<Integer> pgms;

    public ControlePgm(AcaoPgm acao, List<Integer> pgms) {
        super(TipoComando.CONTROLE_DE_PGM);
        this.acao = acao;
        this.pgms = pgms;
    }

    @Override
    public String toString() {
        return "ControlePgm{" +
                "acao='" + acao + '\'' +
                ", pgms=" + pgms +
                '}';
    }
}
