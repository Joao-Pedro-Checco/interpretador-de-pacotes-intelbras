package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.PacoteGenerico;

public interface ProcessadorPacote {
    PacoteGenerico processar(String hexString);
}
