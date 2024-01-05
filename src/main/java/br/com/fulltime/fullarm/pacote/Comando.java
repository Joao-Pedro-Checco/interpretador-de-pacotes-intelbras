package br.com.fulltime.fullarm.pacote;

public class Comando extends PacoteGenerico {
    private final String senha;
    private final String subComando;
    private final String descricaoSubComando;
    private final String checksum;

    public Comando(String senha, String subComando, String descricaoSubComando, String checksum) {
        super(TipoPacote.COMANDO);
        this.senha = senha;
        this.subComando = subComando;
        this.descricaoSubComando = descricaoSubComando;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "Comando{" +
                "senha='" + senha + '\'' +
                ", subComando='" + subComando + '\'' +
                ", descricaoSubComando='" + descricaoSubComando + '\'' +
                ", checksum='" + checksum + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
