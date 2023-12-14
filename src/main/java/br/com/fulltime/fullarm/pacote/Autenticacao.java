package br.com.fulltime.fullarm.pacote;

public class Autenticacao extends Pacote {
    private TipoConexao tipoConexao;
    private String numeroDaConta;
    private String enderecoMac;
    private String checksum;

    public Autenticacao(TipoPacote tipo, TipoConexao tipoConexao, String numeroDaConta, String enderecoMac, String checksum) {
        super(tipo);
        this.tipoConexao = tipoConexao;
        this.numeroDaConta = numeroDaConta;
        this.enderecoMac = enderecoMac;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return this.tipo + " -> " +
                "Conexão: " + this.tipoConexao +
                " | Conta: " + this.numeroDaConta +
                " | MAC: " + this.enderecoMac +
                " | Checksum: " + this.checksum;
    }
}
