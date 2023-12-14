package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Pacote;

public interface ProcessadorPacoteFrameCurto extends ProcessadorPacote {
    Pacote processar(String hexString);
}
