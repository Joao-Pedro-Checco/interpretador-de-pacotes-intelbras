package br.com.fulltime.fullarm.processador;

public interface ProcessadorPacoteFrameLongo extends ProcessadorPacote {
    void particionarBytes(String hexString);
}
