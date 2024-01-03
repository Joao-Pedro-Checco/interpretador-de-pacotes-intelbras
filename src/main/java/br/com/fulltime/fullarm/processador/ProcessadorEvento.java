package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Evento;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.TipoConexao;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessadorEvento implements ProcessadorPacoteFrameLongo {
    @Override
    public PacoteGenerico processar(String hexString) {
        return montarPacote(hexString);
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private PacoteGenerico montarPacote(String hexString) {
        List<String> bytes = particionarBytes(hexString);
        return new Evento(
                TipoPacote.EVENTO, getConexao(bytes), getConta(bytes),
                getContactId(bytes), getQualificador(bytes), getCodigoEvento(bytes),
                getParticao(bytes), getArgumento(bytes), getChecksum(bytes)
        );
    }

    private TipoConexao getConexao(List<String> bytes) {
        String byteConexao = bytes.get(2);
        return switch (byteConexao) {
            case "11" -> TipoConexao.ETHERNET_IP_1;
            case "12" -> TipoConexao.ETHERNET_IP_2;
            case "21" -> TipoConexao.GPRS_IP_1;
            case "22" -> TipoConexao.GPRS_IP_2;
            default -> throw new IllegalArgumentException("Tipo de conexão desconhecido: " + byteConexao);
        };
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
            default -> throw new IllegalArgumentException("Qualificador desconhecido: " + byteQualificador);
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
