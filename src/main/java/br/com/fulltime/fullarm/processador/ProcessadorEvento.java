package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Evento;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.TipoConexao;
import br.com.fulltime.fullarm.pacote.TipoPacote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessadorEvento implements ProcessadorPacoteFrameLongo {
    private List<String> bytes = new ArrayList<>();

    @Override
    public Pacote processar(String hexString) {
        this.particionarBytes(hexString);
        return this.montarPacote();
    }

    @Override
    public void particionarBytes(String hexString) {
        this.bytes = Arrays.asList(hexString.split(" "));
    }

    private Pacote montarPacote() {
        return new Evento(
                TipoPacote.EVENTO, getConexao(), getConta(),
                getContactId(), getQualificador(), getCodigoEvento(),
                getParticao(), getArgumento(), getChecksum()
        );
    }

    private TipoConexao getConexao() {
        String byteConexao = this.bytes.get(2);
        switch (byteConexao) {
            case "11":
                return TipoConexao.ETHERNET_IP_1;
            case "12":
                return TipoConexao.ETHERNET_IP_2;
            case "21":
                return TipoConexao.GPRS_1;
            case "22":
                return TipoConexao.GPRS_IP_2;
            default:
                throw new IllegalArgumentException("Tipo de conexão desconhecido: " + byteConexao);
        }
    }

    private String getConta() {
        List<String> bytesConta = tratarByte0A(this.bytes.subList(3, 7));
        List<String> bytesTratados =
                bytesConta.stream().map(b -> b.equals("00") ? "0" : b.replace("0", ""))
                .collect(Collectors.toList());
        return String.join("", bytesTratados);
    }

    private String getContactId() {
        return converterArrayHexParaDecimal(7, 9);
    }

    private String getQualificador() {
        String byteQualificador = this.bytes.get(9);
        switch (byteQualificador) {
            case "01":
                return "Evento";
            case "03":
                return "Restauração";
            default:
                throw new IllegalArgumentException("Qualificador desconhecido: " + byteQualificador);
        }
    }

    private String getCodigoEvento() {
        return converterArrayHexParaDecimal(10, 13);
    }

    private String getParticao() {
        return converterArrayHexParaDecimal(13, 15);
    }

    private String getArgumento() {
        return converterArrayHexParaDecimal(15, 18);
    }

    private String getChecksum() {
        return this.bytes.get(this.bytes.size() - 1);
    }

    private String converterArrayHexParaDecimal(int inicio, int fim) {
        List<String> bytesTratados = tratarByte0A(this.bytes.subList(inicio, fim));
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
