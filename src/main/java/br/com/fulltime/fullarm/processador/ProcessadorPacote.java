package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Pacote;

public interface ProcessadorPacote {
    Pacote processar(String hexString);
}
