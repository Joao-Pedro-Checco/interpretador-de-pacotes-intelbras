package br.com.fulltime.fullarm.processador.comando;

import br.com.fulltime.fullarm.pacote.TipoComando;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HandlerComando {
    private final CriadorSubComando criadorSubComando;

    public HandlerComando(CriadorSubComando criadorSubComando) {
        this.criadorSubComando = criadorSubComando;
    }

    public String montarComando(String comando) {
        List<String> bytesComando = particionarComando(comando);
        String tipoComando = getTipoComando(bytesComando.get(0)).toString();
        String subComando = getSubComando(bytesComando);
        if (subComando.isEmpty()) return tipoComando;

        return String.format("%s -> %s", tipoComando, subComando);
    }

    private List<String> particionarComando(String comando) {
        return Arrays.asList(comando.split(" "));
    }

    private TipoComando getTipoComando(String byteComando) {
        return TipoComando.getByValue(byteComando);
    }

    private String getSubComando(List<String> bytes) {
        TipoComando tipoComando = getTipoComando(bytes.get(0));
        List<String> bytesSubComando = !bytes.isEmpty() ? bytes.subList(1, bytes.size()) : new ArrayList<>();
        return criadorSubComando.createSubComando(tipoComando, bytesSubComando);
    }
}
