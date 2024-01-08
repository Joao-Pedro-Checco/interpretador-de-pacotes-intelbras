package br.com.fulltime.fullarm.modelo;

import br.com.fulltime.fullarm.constantes.TipoConexao;
import br.com.fulltime.fullarm.constantes.TipoProcessador;

public class Autenticacao extends PacoteGenerico {
    private TipoConexao tipoConexao;
    private String numeroDaConta;
    private String enderecoMac;
    private String checksum;

    public Autenticacao(TipoConexao tipoConexao, String numeroDaConta, String enderecoMac, String checksum) {
        super(TipoProcessador.AUTENTICACAO);
        this.tipoConexao = tipoConexao;
        this.numeroDaConta = numeroDaConta;
        this.enderecoMac = enderecoMac;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return String.format("""
                %s {
                  Conexão: %s
                  Conta: %s
                  MAC: %s
                  Checksum: %s
                }
                """, this.tipo, this.tipoConexao, this.numeroDaConta, this.enderecoMac, this.checksum);
    }
}
