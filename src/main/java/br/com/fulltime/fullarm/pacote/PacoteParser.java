package br.com.fulltime.fullarm.pacote;

import br.com.fulltime.fullarm.factory.PacoteFactory;

public class PacoteParser {
    private String[] bytes;

    public PacoteParser(String hexString) {
        this.bytes = hexString.split(" ");
    }

    public Pacote identificarPacote() {
        if (bytes.length > 1) {
            return new PacoteFactory().createPacote(bytes[1]);
        }
        return new PacoteFactory().createPacote(bytes[0]);
    }
}
