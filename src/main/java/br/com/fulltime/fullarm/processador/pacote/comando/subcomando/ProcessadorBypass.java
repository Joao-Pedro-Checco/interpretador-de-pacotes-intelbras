package br.com.fulltime.fullarm.processador.pacote.comando.subcomando;

import br.com.fulltime.fullarm.modelo.comando.subcomando.Bypass;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;
import br.com.fulltime.fullarm.utils.GerenciadorDeZonas;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorBypass implements ProcessadorSubComandoLongo {
    @Override
    public SubComando processar(String argumentos) {
        List<String> bytes = particionarBytes(argumentos);
        System.out.println("Processando subcomando de Bypass...");
        List<String> bytesZonas = bytes.subList(1, bytes.size());
        List<Integer> zonasAnuladas = GerenciadorDeZonas.buscarZonasComBit1(bytesZonas);
        System.out.println("Adicionando zonas anuladas: " + zonasAnuladas);
        return new Bypass(zonasAnuladas);
    }

    @Override
    public List<String> particionarBytes(String argumentos) {
        return Arrays.asList(argumentos.split(" "));
    }
}
