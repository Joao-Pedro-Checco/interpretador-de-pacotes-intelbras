package br.com.fulltime.fullarm.modelo;

public class Particao {
    private boolean ativada;

    public boolean isAtivada() {
        return ativada;
    }

    public void setAtivada(boolean ativada) {
        this.ativada = ativada;
    }

    @Override
    public String toString() {
        return "Particao{" +
                "ativada=" + ativada +
                '}';
    }
}
