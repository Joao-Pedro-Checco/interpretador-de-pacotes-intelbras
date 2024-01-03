package br.com.fulltime.fullarm.pacote;

public class Comando extends PacoteGenerico {
    private final String senha;
    private final String subComando;
    private final String descricaoSubComando;
    private final String checksum;

    public Comando(TipoPacote tipo, String senha, String subComando, String descricaoSubComando, String checksum) {
        super(tipo);
        this.senha = senha;
        this.subComando = subComando;
        this.descricaoSubComando = descricaoSubComando;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return String.format("""
                %s {
                  Senha: %s
                  Sub comando: %s
                  Descrição do sub comando: %s
                  Checksum: %s
                }
                """, this.tipo, this.senha, this.subComando, this.descricaoSubComando, this.checksum);
    }
}
