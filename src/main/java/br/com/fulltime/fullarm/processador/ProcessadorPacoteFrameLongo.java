package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Pacote;

public interface ProcessadorPacoteFrameLongo extends ProcessadorPacote {
    Pacote processar(String hexString);

    void particionarBytes(String hexString);
}
