package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComandoDesconhecido;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorSubComandoDesconhecido implements ProcessadorSubComando {
    @Override
    public SubComando processar(String argumentos) {
        return new SubComandoDesconhecido();
    }
}
