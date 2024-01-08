package br.com.fulltime.fullarm.processador.comando.subcomando;

import br.com.fulltime.fullarm.constantes.AcaoPgm;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.ControlePgm;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessadorControlePgm implements ProcessadorSubComandoLongo {
    @Override
    public SubComando processar(String argumentos) {
        List<String> bytesComando = particionarBytes(argumentos);
        System.out.println("Processando subcomando Controle de PGM...");
        AcaoPgm acaoPgm = AcaoPgm.getByValue(bytesComando.get(1));
        System.out.println("Adicionando Ação da Pgm: " + acaoPgm);
        List<Integer> pgms = buscarPgms(bytesComando);

        return new ControlePgm(acaoPgm, pgms);
    }

    @Override
    public List<String> particionarBytes(String argumentos) {
        return Arrays.asList(argumentos.split(" "));
    }

    private List<Integer> buscarPgms(List<String> bytes) {
        List<String> bytesPgms = bytes.subList(2, bytes.size());
        return bytesPgms.stream().map(b -> Integer.parseInt(b, 16) - 0x30).collect(Collectors.toList());
    }
}
