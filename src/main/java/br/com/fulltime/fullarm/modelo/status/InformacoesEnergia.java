package br.com.fulltime.fullarm.modelo.status;

public class InformacoesEnergia {
    private boolean faltaDeRedeEletrica;
    private boolean bateriaBaixa;
    private boolean bateriaAusenteOuInvertida;
    private boolean bateriaEmCurtoCircuito;
    private boolean sobrecargaNaSaidaAuxiliar;

    public void setFaltaDeRedeEletrica(boolean faltaDeRedeEletrica) {
        this.faltaDeRedeEletrica = faltaDeRedeEletrica;
    }

    public void setBateriaBaixa(boolean bateriaBaixa) {
        this.bateriaBaixa = bateriaBaixa;
    }

    public void setBateriaAusenteOuInvertida(boolean bateriaAusenteOuInvertida) {
        this.bateriaAusenteOuInvertida = bateriaAusenteOuInvertida;
    }

    public void setBateriaEmCurtoCircuito(boolean bateriaEmCurtoCircuito) {
        this.bateriaEmCurtoCircuito = bateriaEmCurtoCircuito;
    }

    public void setSobrecargaNaSaidaAuxiliar(boolean sobrecargaNaSaidaAuxiliar) {
        this.sobrecargaNaSaidaAuxiliar = sobrecargaNaSaidaAuxiliar;
    }

    @Override
    public String toString() {
        return "InformacoesEnergia{" +
                "faltaDeRedeEletrica=" + faltaDeRedeEletrica +
                ", bateriaBaixa=" + bateriaBaixa +
                ", bateriaAusenteOuInvertida=" + bateriaAusenteOuInvertida +
                ", bateriaEmCurtoCircuito=" + bateriaEmCurtoCircuito +
                ", sobrecargaNaSaidaAuxiliar=" + sobrecargaNaSaidaAuxiliar +
                '}';
    }
}
