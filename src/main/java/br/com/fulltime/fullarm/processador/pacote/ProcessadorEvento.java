package br.com.fulltime.fullarm.processador.pacote;

import br.com.fulltime.fullarm.modelo.Evento;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import br.com.fulltime.fullarm.constantes.TipoConexao;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessadorEvento implements ProcessadorPacoteFrameLongo {
    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);

        System.out.println("Montando pacote de Evento...");
        System.out.println("===================================================================================");

        TipoConexao conexao = getConexao(bytes);
        System.out.println("Adicionando Tipo de Conexão: " + conexao);

        String conta = getConta(bytes);
        System.out.println("Adicionando Número da conta: " + conta);

        String contactId = getContactId(bytes);
        System.out.println("Adicionando Contact Id: " + contactId);

        String qualificador = getQualificador(bytes);
        System.out.println("Adicionando Qualificador: " + qualificador);

        String codigoEvento = getCodigoEvento(bytes);
        System.out.println("Adicionando Código do Evento: " + codigoEvento);

        String particao = getParticao(bytes);
        System.out.println("Adicionando Partição: " + particao);

        String argumento = getArgumento(bytes);
        System.out.println("Adicionando Argumento: " + argumento);

        String checksum = getChecksum(bytes);
        System.out.println("Adicionando checksum: " + checksum);
        System.out.println("===================================================================================");

        return new Evento(conexao, conta, contactId,
                qualificador, codigoEvento, particao, argumento, checksum);
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private TipoConexao getConexao(List<String> bytes) {
        String byteConexao = bytes.get(2);
        return TipoConexao.getByValue(byteConexao);
    }

    private String getConta(List<String> bytes) {
        List<String> bytesConta = tratarByte0A(bytes.subList(3, 7));
        List<String> bytesTratados =
                bytesConta.stream().map(b -> b.equals("00") ? "0" : b.replace("0", ""))
                .collect(Collectors.toList());
        return String.join("", bytesTratados);
    }

    private String getContactId(List<String> bytes) {
        return converterArrayHexParaDecimal(bytes, 7, 9);
    }

    private String getQualificador(List<String> bytes) {
        String byteQualificador = bytes.get(9);
        return switch (byteQualificador) {
            case "01" -> "Evento";
            case "03" -> "Restauração";
            default -> "Qualificador desconhecido: " + byteQualificador;
        };
    }

    private String getCodigoEvento(List<String> bytes) {
        return converterArrayHexParaDecimal(bytes, 10, 13);
    }

    private String getParticao(List<String> bytes) {
        return converterArrayHexParaDecimal(bytes, 13, 15);
    }

    private String getArgumento(List<String> bytes) {
        return converterArrayHexParaDecimal(bytes, 15, 18);
    }

    private String getChecksum(List<String> bytes) {
        return bytes.get(bytes.size() - 1);
    }

    private String converterArrayHexParaDecimal(List<String> bytes, int inicio, int fim) {
        List<String> bytesTratados = tratarByte0A(bytes.subList(inicio, fim));
        List<String> bytesConvertidos =
                bytesTratados.stream().map(b -> String.valueOf(Integer.valueOf(b, 16)))
                .collect(Collectors.toList());
        return String.join("", bytesConvertidos);
    }

    private List<String> tratarByte0A(List<String> bytes) {
        return bytes.stream().map(b -> b.equals("0A") ? "00" : b)
                .collect(Collectors.toList());
    }
}
