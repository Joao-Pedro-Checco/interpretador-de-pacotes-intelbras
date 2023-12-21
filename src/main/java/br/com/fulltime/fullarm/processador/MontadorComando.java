package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.factory.SubComandoFactory;
import br.com.fulltime.fullarm.pacote.TipoComando;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MontadorComando {
    private final SubComandoFactory factory;

    public MontadorComando(SubComandoFactory factory) {
        this.factory = factory;
    }

    public String montar(String comando) {
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
        return factory.createSubComando(tipoComando, bytesSubComando);
    }
}
