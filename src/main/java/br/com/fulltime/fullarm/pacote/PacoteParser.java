package br.com.fulltime.fullarm.pacote;

import br.com.fulltime.fullarm.factory.PacoteFactory;

public class PacoteParser {
    private String[] bytes;

    public PacoteParser(String hexString) {
        this.bytes = hexString.split(" ");
    }

    public Pacote identificarPacote() {
        if (ehFrameLongo())
            return new PacoteFactory().criar(bytes[1]);
        return new PacoteFactory().criar(bytes[0]);
    }

    private boolean ehFrameLongo() {
        return bytes.length > 1;
    }
}
