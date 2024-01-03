package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Autenticacao;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.TipoConexao;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorAutenticacao implements ProcessadorPacoteFrameLongo {
    @Override
    public PacoteGenerico processar(String hexString) {
        return montarPacote(hexString);
    }

    private PacoteGenerico montarPacote(String hexString) {
        List<String> bytes = particionarBytes(hexString);
        TipoConexao conexao = getTipoConexao(bytes);
        String conta = getNumeroDaConta(bytes);
        String enderecoMac = getEnderecoMac(bytes);
        String checksum = getChecksum(bytes);
        return new Autenticacao(TipoPacote.AUTENTICACAO, conexao, conta, enderecoMac, checksum);
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private TipoConexao getTipoConexao(List<String> bytes) {
        String byteConexao = bytes.get(2);
        return switch (byteConexao) {
            case "45" -> TipoConexao.ETHERNET;
            case "47" -> TipoConexao.GPRS_1;
            case "48" -> TipoConexao.GPRS_2;
            default -> throw new IllegalArgumentException("Tipo de conexão desconhecido: " + byteConexao);
        };
    }

    private String getNumeroDaConta(List<String> bytes) {
        List<String> arrayConta = bytes.subList(3, 5);
        return String.join("", arrayConta);
    }

    private String getEnderecoMac(List<String> bytes) {
        List<String> arrayMac = bytes.subList(5, 8);
        return String.join("", arrayMac);
    }

    private String getChecksum(List<String> bytes) {
        return bytes.get(bytes.size() - 1);
    }
}
