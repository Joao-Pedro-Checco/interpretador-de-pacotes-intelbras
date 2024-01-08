package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComandoDesconhecido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessadorSubComandoDesconhecido implements ProcessadorSubComando {
    @Override
    public SubComando processar(String argumentos) {
        return new SubComandoDesconhecido();
    }
}
