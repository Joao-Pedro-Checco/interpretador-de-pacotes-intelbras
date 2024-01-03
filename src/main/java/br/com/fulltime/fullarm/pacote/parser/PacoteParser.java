package br.com.fulltime.fullarm.pacote.parser;

import br.com.fulltime.fullarm.pacote.factory.PacoteFactory;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PacoteParser {
    private final PacoteFactory factory;

    public PacoteParser(PacoteFactory factory) {
        this.factory = factory;
    }

    public ProcessadorPacote identificarPacote(String hexString) {
        List<String> bytes = Arrays.asList(hexString.split(" "));
        boolean ehFrameLongo = bytes.size() > 1;
        if (!ehFrameLongo) {
            return factory.buscarProcessador(bytes.get(0));
        }

        int byteTamanho = Integer.valueOf(bytes.get(0), 16);
        if (byteTamanho == 44) return factory.buscarProcessador("STP");
        if (byteTamanho == 55) return factory.buscarProcessador("STC");
        return factory.buscarProcessador(bytes.get(1));
    }
}
