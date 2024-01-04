package br.com.fulltime.fullarm.modelo;

public class Pgm {
    private boolean ligada;

    public void setLigada(boolean ligada) {
        this.ligada = ligada;
    }

    @Override
    public String toString() {
        return "Pgm{" +
                "ligada=" + ligada +
                '}';
    }
}
