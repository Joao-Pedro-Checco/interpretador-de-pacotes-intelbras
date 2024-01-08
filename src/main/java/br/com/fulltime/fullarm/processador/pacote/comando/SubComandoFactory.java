package br.com.fulltime.fullarm.processador.pacote.comando;

import br.com.fulltime.fullarm.constantes.TipoComando;
import br.com.fulltime.fullarm.processador.pacote.comando.subcomando.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SubComandoFactory {
    private final ProcessadorAtivacaoCentral processadorAtivacaoCentral;
    private final ProcessadorDesativacaoCentral processadorDesativacaoCentral;
    private final ProcessadorBypass processadorBypass;
    private final ProcessadorControlePgm processadorControlePgm;
    private final ProcessadorPanico processadorPanico;
    private final ProcessadorSolicitacaoStatusParcial processadorSolicitacaoStatusParcial;
    private final ProcessadorSolicitacaoStatusCompleto processadorSolicitacaoStatusCompleto;
    private final ProcessadorLigaSirene processadorLigaSirene;
    private final ProcessadorDesligaSirene processadorDesligaSirene;
    private final ProcessadorSubComandoDesconhecido processadorSubComandoDesconhecido;
    private final HashMap<TipoComando, ProcessadorSubComando> mapaProcessadores = new HashMap<>();

    public SubComandoFactory(ProcessadorAtivacaoCentral processadorAtivacaoCentral,
                             ProcessadorDesativacaoCentral processadorDesativacaoCentral,
                             ProcessadorBypass processadorBypass,
                             ProcessadorControlePgm processadorControlePgm,
                             ProcessadorPanico processadorPanico,
                             ProcessadorSolicitacaoStatusParcial processadorSolicitacaoStatusParcial,
                             ProcessadorSolicitacaoStatusCompleto processadorSolicitacaoStatusCompleto,
                             ProcessadorLigaSirene processadorLigaSirene,
                             ProcessadorDesligaSirene processadorDesligaSirene,
                             ProcessadorSubComandoDesconhecido processadorSubComandoDesconhecido) {
        this.processadorAtivacaoCentral = processadorAtivacaoCentral;
        this.processadorDesativacaoCentral = processadorDesativacaoCentral;
        this.processadorBypass = processadorBypass;
        this.processadorControlePgm = processadorControlePgm;
        this.processadorPanico = processadorPanico;
        this.processadorSolicitacaoStatusParcial = processadorSolicitacaoStatusParcial;
        this.processadorSolicitacaoStatusCompleto = processadorSolicitacaoStatusCompleto;
        this.processadorLigaSirene = processadorLigaSirene;
        this.processadorDesligaSirene = processadorDesligaSirene;
        this.processadorSubComandoDesconhecido = processadorSubComandoDesconhecido;
    }

    private void inicializarMapa() {
        mapaProcessadores.put(TipoComando.ATIVACAO_DA_CENTRAL, processadorAtivacaoCentral);
        mapaProcessadores.put(TipoComando.DESATIVACAO_DA_CENTRAL, processadorDesativacaoCentral);
        mapaProcessadores.put(TipoComando.BYPASS, processadorBypass);
        mapaProcessadores.put(TipoComando.CONTROLE_DE_PGM, processadorControlePgm);
        mapaProcessadores.put(TipoComando.PANICO, processadorPanico);
        mapaProcessadores.put(TipoComando.SOLICITACAO_PARCIAL_STATUS, processadorSolicitacaoStatusParcial);
        mapaProcessadores.put(TipoComando.SOLICITACAO_COMPLETA_STATUS, processadorSolicitacaoStatusCompleto);
        mapaProcessadores.put(TipoComando.LIGA_SIRENE, processadorLigaSirene);
        mapaProcessadores.put(TipoComando.DESLIGA_SIRENE, processadorDesligaSirene);
    }

    public ProcessadorSubComando buscarProcessador(TipoComando tipoComando) {
        if (mapaProcessadores.isEmpty()) inicializarMapa();
        return mapaProcessadores.getOrDefault(tipoComando, processadorSubComandoDesconhecido);
    }
}
