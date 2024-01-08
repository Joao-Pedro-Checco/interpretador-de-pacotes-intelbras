package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.AtivacaoCentral;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessadorAtivacaoCentral implements ProcessadorSubComandoLongo {
    public final static String mapaAlfabeto = "ABCDEF";

    @Override
    public SubComando processar(String argumentos) {
        List<String> bytes = particionarBytes(argumentos);
        System.out.println("Processando subcomando de Ativação da Central...");
        List<Character> particoes = buscarParticoes(bytes);
        System.out.println("Adicionando partições: " + particoes);
        return new AtivacaoCentral(particoes);
    }

    @Override
    public List<String> particionarBytes(String argumentos) {
        return Arrays.asList(argumentos.split(" "));
    }

    private List<Character> buscarParticoes(List<String> bytes) {
        if (bytes.size() == 1) return Arrays.asList('A', 'B', 'C', 'D');

        List<String> bytesParticoes = bytes.subList(1, bytes.size());
        return bytesParticoes.stream()
                .map(b -> Integer.parseInt(b, 16) - 0x41)
                .map(mapaAlfabeto::charAt)
                .collect(Collectors.toList());
    }
}
