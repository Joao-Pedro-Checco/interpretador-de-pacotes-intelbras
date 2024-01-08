package br.com.fulltime.fullarm.modelo.pacote.parser;

import br.com.fulltime.fullarm.constantes.TipoPacote;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PacoteParser {
    public TipoPacote identificarPacote(String hexString) {
        List<String> bytes = Arrays.asList(hexString.split(" "));
        boolean ehFrameLongo = bytes.size() > 1;
        if (!ehFrameLongo) {
            return TipoPacote.getByValue(bytes.get(0));
        }

        int byteTamanho = Integer.valueOf(bytes.get(0), 16);
        if (byteTamanho == 44) return TipoPacote.STATUS_PARCIAL;
        if (byteTamanho == 55) return TipoPacote.STATUS_COMPLETO;
        return TipoPacote.getByValue(bytes.get(1));
    }
}
