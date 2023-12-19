package br.com.fulltime.fullarm.pacote;

import br.com.fulltime.fullarm.factory.PacoteFactory;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;

public class PacoteParser {
    private PacoteFactory factory;

    public PacoteParser(PacoteFactory factory) {
        this.factory = factory;
    }

    public ProcessadorPacote identificarPacote(String hexString) {
        String[] bytes = hexString.split(" ");

        if (ehFrameLongo(bytes))
            return factory.buscarProcessador(bytes[1]);
        return factory.buscarProcessador(bytes[0]);
    }

    private boolean ehFrameLongo(String[] bytes) {
        return bytes.length > 1;
    }
}
