package br.com.fulltime.fullarm.modelo.status;

public class Teclado {
    private boolean problema;
    private boolean tamper;

    public void setProblema(boolean problema) {
        this.problema = problema;
    }

    public void setTamper(boolean tamper) {
        this.tamper = tamper;
    }

    @Override
    public String toString() {
        return "Teclado{" +
                "problema=" + problema +
                ", tamper=" + tamper +
                '}';
    }
}
