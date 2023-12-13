package br.com.fulltime.fullarm.pacote;

import br.com.fulltime.fullarm.factory.PacoteFactory;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;

public class PacoteParser {
    private String[] bytes;

    public PacoteParser(String hexString) {
        this.bytes = hexString.split(" ");
    }

    public ProcessadorPacote identificarPacote() {
        if (ehFrameLongo())
            return new PacoteFactory().buscarProcessador(bytes[1]);
        return new PacoteFactory().buscarProcessador(bytes[0]);
    }

    private boolean ehFrameLongo() {
        return bytes.length > 1;
    }
}
