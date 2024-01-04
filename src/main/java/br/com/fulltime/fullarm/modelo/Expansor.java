package br.com.fulltime.fullarm.modelo;

public class Expansor {
    private boolean problema;

    public void setProblema(boolean problema) {
        this.problema = problema;
    }

    @Override
    public String toString() {
        return "Expansor{" +
                "problema=" + problema +
                '}';
    }
}
