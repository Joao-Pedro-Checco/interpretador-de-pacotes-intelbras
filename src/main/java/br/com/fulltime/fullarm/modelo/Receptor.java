package br.com.fulltime.fullarm.modelo;

public class Receptor {
    private boolean problema;

    public void setProblema(boolean problema) {
        this.problema = problema;
    }

    @Override
    public String toString() {
        return "Receptor{" +
                "problema=" + problema +
                '}';
    }
}
