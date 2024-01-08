package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import java.util.List;

public interface ProcessadorSubComandoLongo extends ProcessadorSubComando {
    List<String> particionarBytes(String argumentos);
}
