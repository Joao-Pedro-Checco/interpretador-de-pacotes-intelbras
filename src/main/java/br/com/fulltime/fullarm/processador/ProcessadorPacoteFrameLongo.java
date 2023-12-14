package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Pacote;

public interface ProcessadorPacoteFrameLongo extends ProcessadorPacote {
    Pacote processar(String hexString);

    String[] particionarBytes(String hexString);
}
