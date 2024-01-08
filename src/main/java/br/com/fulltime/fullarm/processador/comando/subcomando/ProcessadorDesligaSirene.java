package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.DesligaSirene;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorDesligaSirene implements ProcessadorSubComandoByteUnico {
    @Override
    public SubComando processar(String argumentos) {
        System.out.println("Processando subcomando de Desligar Sirene...");
        return new DesligaSirene();
    }
}
