package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.modelo.comando.subcomando.SolicitacaoStatusParcial;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorSolicitacaoStatusParcial implements ProcessadorSubComandoByteUnico {
    @Override
    public SubComando processar(String argumentos) {
        System.out.println("Processando subcomando de Solicitação de Status Parcial...");
        return new SolicitacaoStatusParcial();
    }
}
