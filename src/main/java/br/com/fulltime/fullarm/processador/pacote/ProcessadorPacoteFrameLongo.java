package br.com.fulltime.fullarm.processador.pacote;

import java.util.List;

public interface ProcessadorPacoteFrameLongo extends ProcessadorPacote {
    List<String> particionarBytes(String hexString);
}
