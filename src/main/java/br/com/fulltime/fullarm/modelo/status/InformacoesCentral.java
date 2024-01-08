package br.com.fulltime.fullarm.modelo.status;

public class InformacoesCentral {
    private boolean problemasDetectados;
    private boolean sireneLigada;
    private boolean existemZonasDisparadas;
    private boolean centralAtivada;

    public void setProblemasDetectados(boolean problemasDetectados) {
        this.problemasDetectados = problemasDetectados;
    }

    public void setSireneLigada(boolean sireneLigada) {
        this.sireneLigada = sireneLigada;
    }

    public void setExistemZonasDisparadas(boolean existemZonasDisparadas) {
        this.existemZonasDisparadas = existemZonasDisparadas;
    }

    public void setCentralAtivada(boolean centralAtivada) {
        this.centralAtivada = centralAtivada;
    }

    @Override
    public String toString() {
        return "StatusCentral{" +
                "problemasDetectados=" + problemasDetectados +
                ", sireneLigada=" + sireneLigada +
                ", existemZonasDisparadas=" + existemZonasDisparadas +
                ", centralAtivada=" + centralAtivada +
                '}';
    }
}
