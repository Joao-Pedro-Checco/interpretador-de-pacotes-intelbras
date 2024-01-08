package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.LigaSirene;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorLigaSirene implements ProcessadorSubComandoByteUnico {
    @Override
    public SubComando processar(String argumentos) {
        System.out.println("Processando subcomando de Ligar Sirene...");
        return new LigaSirene();
    }
}
