package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.constantes.TipoPanico;
import br.com.fulltime.fullarm.modelo.comando.subcomando.Panico;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorPanico implements ProcessadorSubComandoLongo {
    @Override
    public SubComando processar(String argumentos) {
        List<String> bytes = particionarBytes(argumentos);
        System.out.println("Processando subcomando de Pânico...");
        TipoPanico tipoPanico = TipoPanico.getByValue(bytes.get(1));
        System.out.println("Adicionando tipo de pânico: " + tipoPanico);
        return new Panico(tipoPanico);
    }

    @Override
    public List<String> particionarBytes(String argumentos) {
        return Arrays.asList(argumentos.split(" "));
    }
}
