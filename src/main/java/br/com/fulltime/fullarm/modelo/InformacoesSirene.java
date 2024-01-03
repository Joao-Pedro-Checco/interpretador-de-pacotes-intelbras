package br.com.fulltime.fullarm.modelo;

public class InformacoesSirene {
    private boolean sireneLigada;
    private boolean corteDoFioDaSirene;
    private boolean curtoCircuitoNoFioDaSirene;

    public void setSireneLigada(boolean sireneLigada) {
        this.sireneLigada = sireneLigada;
    }

    public void setCorteDoFioDaSirene(boolean corteDoFioDaSirene) {
        this.corteDoFioDaSirene = corteDoFioDaSirene;
    }

    public void setCurtoCircuitoNoFioDaSirene(boolean curtoCircuitoNoFioDaSirene) {
        this.curtoCircuitoNoFioDaSirene = curtoCircuitoNoFioDaSirene;
    }
}
