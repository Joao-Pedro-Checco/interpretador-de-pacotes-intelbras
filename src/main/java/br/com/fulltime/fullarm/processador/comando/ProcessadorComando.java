package br.com.fulltime.fullarm.processador.comando;

import br.com.fulltime.fullarm.pacote.Comando;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.processador.ProcessadorPacoteFrameLongo;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorComando implements ProcessadorPacoteFrameLongo {
    private final HandlerComando hanlderComando;

    public ProcessadorComando(HandlerComando handlerComando) {
        this.hanlderComando = handlerComando;
    }

    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);

        System.out.println("Montando pacote de Comando...");
        System.out.println("===================================================================================");

        String senha = getSenha(bytes);
        System.out.println("Adicionando Senha: " + senha);
        System.out.println("===================================================================================");

        String comando = getComando(bytes);
        System.out.println("Adicionando o comando: " + comando);
        System.out.println("===================================================================================");

        String descricaoComando = getDescricaoComando(bytes);
        System.out.println("Adicionando Dscrição do comando: " + descricaoComando);
        System.out.println("===================================================================================");

        String checksum = getChecksum(bytes);
        System.out.println("Adicionando checksum: " + checksum);
        System.out.println("===================================================================================");

        return new Comando(senha, comando, descricaoComando, checksum);
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private String getSenha(List<String> bytes) {
        String hexStringSenha = String.join("", bytes.subList(3, 9));
        return FormatadorHexStr.hexParaAscii(hexStringSenha);
    }

    private String getComando(List<String> bytes) {
        int inicioComando = 9;
        List<String> bytesSenha = new ArrayList<>();
        for (int i = inicioComando; !bytes.get(i).equals("21"); i++) {
            bytesSenha.add(bytes.get(i));
        }

        return String.join(" ", bytesSenha);
    }

    private String getDescricaoComando(List<String> bytes) {
        return hanlderComando.montarComando(getComando(bytes));
    }

    private String getChecksum(List<String> bytes) {
        return bytes.get(bytes.size() - 1);
    }
}
