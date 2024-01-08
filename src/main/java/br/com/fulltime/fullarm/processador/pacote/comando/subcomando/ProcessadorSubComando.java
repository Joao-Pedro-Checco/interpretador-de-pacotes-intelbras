package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;

public interface ProcessadorSubComando {
    SubComando processar(String argumento);
}
