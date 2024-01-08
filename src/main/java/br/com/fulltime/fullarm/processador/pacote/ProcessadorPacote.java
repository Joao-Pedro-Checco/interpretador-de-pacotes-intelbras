package br.com.fulltime.fullarm.processador.pacote;

import br.com.fulltime.fullarm.modelo.PacoteGenerico;

public interface ProcessadorPacote {
    PacoteGenerico processar(String hexString);
}
