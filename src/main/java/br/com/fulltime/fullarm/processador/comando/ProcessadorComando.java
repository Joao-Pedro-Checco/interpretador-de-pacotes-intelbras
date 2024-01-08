package br.com.fulltime.fullarm.processador.comando;

import br.com.fulltime.fullarm.constantes.TipoComando;
import br.com.fulltime.fullarm.modelo.pacote.comando.Comando;
import br.com.fulltime.fullarm.modelo.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import br.com.fulltime.fullarm.processador.ProcessadorPacoteFrameLongo;
import br.com.fulltime.fullarm.processador.comando.subcomando.ProcessadorSubComando;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorComando implements ProcessadorPacoteFrameLongo {
    private final ParserSubComando parserSubComando;
    private final SubComandoFactory subComandoFactory;

    public ProcessadorComando(ParserSubComando parserSubComando, SubComandoFactory subComandoFactory) {
        this.parserSubComando = parserSubComando;
        this.subComandoFactory = subComandoFactory;
    }

    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);

        System.out.println("Montando pacote de Comando...");
        System.out.println("===================================================================================");

        String senha = getSenha(bytes);
        System.out.println("Adicionando Senha: " + senha);

        String comando = getComando(bytes);
        System.out.println("Adicionando o comando: " + comando);

        SubComando subComando = getSubComando(bytes);
        System.out.println("Adicionando SubComando: " + subComando);

        String checksum = getChecksum(bytes);
        System.out.println("Adicionando checksum: " + checksum);
        System.out.println("===================================================================================");

        return new Comando(senha, comando, subComando, checksum);
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

    private SubComando getSubComando(List<String> bytes) {
        String comando = getComando(bytes);
        TipoComando identificador = parserSubComando.identificarTipoComando(comando);
        ProcessadorSubComando processadorSubComando = subComandoFactory.buscarProcessador(identificador);
        return processadorSubComando.processar(comando);
    }

    private String getChecksum(List<String> bytes) {
        return bytes.get(bytes.size() - 1);
    }
}
