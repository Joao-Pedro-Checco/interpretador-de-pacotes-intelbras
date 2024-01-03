package br.com.fulltime.fullarm.modelo;

import java.util.List;

public class InformacoesZonas {
    private List<Integer> zonasAbertas;
    private List<Integer> zonasVioladas;
    private List<Integer> zonasAnuladas;
    private List<Integer> zonasComTamper;
    private List<Integer> zonasComCurtoCircuito;
    private List<Integer> zonasComBateriaBaixaNoSensor;

    public void setZonasAbertas(List<Integer> zonasAbertas) {
        this.zonasAbertas = zonasAbertas;
    }

    public void setZonasVioladas(List<Integer> zonasVioladas) {
        this.zonasVioladas = zonasVioladas;
    }

    public void setZonasAnuladas(List<Integer> zonasAnuladas) {
        this.zonasAnuladas = zonasAnuladas;
    }

    public void setZonasComTamper(List<Integer> zonasComTamper) {
        this.zonasComTamper = zonasComTamper;
    }

    public void setZonasComCurtoCircuito(List<Integer> zonasComCurtoCircuito) {
        this.zonasComCurtoCircuito = zonasComCurtoCircuito;
    }

    public void setZonasComBateriaBaixaNoSensor(List<Integer> zonasComBateriaBaixaNoSensor) {
        this.zonasComBateriaBaixaNoSensor = zonasComBateriaBaixaNoSensor;
    }
}
