package br.com.fulltime.fullarm.processador;

import java.util.List;

public interface ProcessadorPacoteFrameLongo extends ProcessadorPacote {
    List<String> particionarBytes(String hexString);
}
