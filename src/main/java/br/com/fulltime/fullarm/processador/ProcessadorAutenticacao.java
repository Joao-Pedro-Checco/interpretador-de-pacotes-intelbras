package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Autenticacao;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.TipoConexao;
import br.com.fulltime.fullarm.pacote.TipoPacote;

import java.util.Arrays;

public class ProcessadorAutenticacao implements ProcessadorPacoteFrameLongo {
    @Override
    public Pacote processar(String hexString) {
        return this.montarPacote(hexString);
    }

    private Pacote montarPacote(String hexString) {
        TipoConexao conexao = getTipoConexao(hexString);
        String conta = getNumeroDaConta(hexString);
        String enderecoMac = getEnderecoMac(hexString);
        String checksum = getChecksum(hexString);
        return new Autenticacao(TipoPacote.AUTENTICACAO, conexao, conta, enderecoMac, checksum);
    }

    @Override
    public String[] particionarBytes(String hexString) {
        return hexString.split(" ");
    }

    private TipoConexao getTipoConexao(String hexString) {
        String byteConexao = particionarBytes(hexString)[2];
        switch(byteConexao) {
            case "45":
                return TipoConexao.ETHERNET;
            case "47":
                return TipoConexao.GPRS_1;
            case "48":
                return TipoConexao.GPRS_2;
            default:
                throw new IllegalArgumentException("Tipo de conexão desconhecido: " + byteConexao);
        }
    }

    private String getNumeroDaConta(String hexString) {
        String[] arrayConta = Arrays.copyOfRange(hexString.split(" "), 3, 5);
        return String.join("", arrayConta);
    }

    private String getEnderecoMac(String hexString) {
        String[] arrayMac = Arrays.copyOfRange(hexString.split(" "), 5, 8);
        return String.join("", arrayMac);
    }

    private String getChecksum(String hexString) {
        String[] array = this.particionarBytes(hexString);
        return array[array.length - 1];
    }
}
