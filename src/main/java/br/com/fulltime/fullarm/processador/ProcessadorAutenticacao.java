package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Autenticacao;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.TipoConexao;
import br.com.fulltime.fullarm.pacote.TipoPacote;

import java.util.Arrays;

public class ProcessadorAutenticacao implements ProcessadorPacoteFrameLongo {
    private String[] bytes;

    @Override
    public Pacote processar(String hexString) {
        this.particionarBytes(hexString);
        return this.montarPacote();
    }

    private Pacote montarPacote() {
        TipoConexao conexao = getTipoConexao();
        String conta = getNumeroDaConta();
        String enderecoMac = getEnderecoMac();
        String checksum = getChecksum();
        return new Autenticacao(TipoPacote.AUTENTICACAO, conexao, conta, enderecoMac, checksum);
    }

    @Override
    public void particionarBytes(String hexString) {
        this.bytes = hexString.split(" ");
    }

    private TipoConexao getTipoConexao() {
        String byteConexao = this.bytes[2];
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

    private String getNumeroDaConta() {
        String[] arrayConta = Arrays.copyOfRange(this.bytes, 3, 5);
        return String.join("", arrayConta);
    }

    private String getEnderecoMac() {
        String[] arrayMac = Arrays.copyOfRange(this.bytes, 5, 8);
        return String.join("", arrayMac);
    }

    private String getChecksum() {
        return this.bytes[this.bytes.length - 1];
    }
}
