package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;

public interface ProcessadorSubComando {
    SubComando processar(String argumento);
}
