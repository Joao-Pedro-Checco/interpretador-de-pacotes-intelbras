package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.modelo.comando.subcomando.SolicitacaoStatusCompleto;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorSolicitacaoStatusCompleto implements ProcessadorSubComandoByteUnico {
    @Override
    public SubComando processar(String argumentos) {
        System.out.println("Processando subcomando de Solicitação de Status Completo...");
        return new SolicitacaoStatusCompleto();
    }
}
