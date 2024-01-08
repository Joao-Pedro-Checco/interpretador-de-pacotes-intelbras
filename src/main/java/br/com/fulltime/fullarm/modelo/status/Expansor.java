package br.com.fulltime.fullarm.modelo.status;

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
