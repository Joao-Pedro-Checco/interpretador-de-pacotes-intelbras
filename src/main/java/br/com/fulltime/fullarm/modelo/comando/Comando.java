package br.com.fulltime.fullarm.modelo.comando;

import br.com.fulltime.fullarm.constantes.TipoProcessador;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import br.com.fulltime.fullarm.modelo.comando.subcomando.SubComando;

public class Comando extends PacoteGenerico {
    private String senha;
    private String comando;
    private SubComando subComando;
    private String checksum;

    public Comando(String senha, String comando, SubComando subComando, String checksum) {
        super(TipoProcessador.COMANDO);
        this.senha = senha;
        this.comando = comando;
        this.subComando = subComando;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "Comando{" +
                "senha='" + senha + '\'' +
                ", comando='" + comando + '\'' +
                ", subComando='" + subComando + '\'' +
                ", checksum='" + checksum + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
