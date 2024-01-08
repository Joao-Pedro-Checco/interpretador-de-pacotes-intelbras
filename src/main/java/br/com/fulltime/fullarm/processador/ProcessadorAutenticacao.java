package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.modelo.pacote.Autenticacao;
import br.com.fulltime.fullarm.modelo.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.constantes.TipoConexao;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorAutenticacao implements ProcessadorPacoteFrameLongo {
    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);

        System.out.println("Montando pacote de Autenticação...");
        System.out.println("===================================================================================");

        TipoConexao conexao = getTipoConexao(bytes);
        System.out.println("Adicionando Tipo de Conexão: " + conexao);

        String conta = getNumeroDaConta(bytes);
        System.out.println("Adicionando Número da conta: " + conta);

        String enderecoMac = getEnderecoMac(bytes);
        System.out.println("Adicionando Endereço MAC: " + enderecoMac);

        String checksum = getChecksum(bytes);
        System.out.println("Adicionando checksum: " + checksum);
        System.out.println("===================================================================================");

        return new Autenticacao(conexao, conta, enderecoMac, checksum);
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private TipoConexao getTipoConexao(List<String> bytes) {
        String byteConexao = bytes.get(2);
        return TipoConexao.getByValue(byteConexao);
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
