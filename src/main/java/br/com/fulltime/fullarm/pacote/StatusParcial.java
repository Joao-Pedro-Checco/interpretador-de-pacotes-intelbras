package br.com.fulltime.fullarm.pacote;

import br.com.fulltime.fullarm.modelo.*;
import br.com.fulltime.fullarm.processador.status.ModeloCentral;

import java.time.LocalDateTime;
import java.util.List;

public class StatusParcial extends PacoteGenerico {
    private InformacoesZonas informacoesZonas;
    private ModeloCentral modeloCentral;
    private String versaoFirmware;
    private StatusParticao statusParticao;
    private List<Particao> particoes;
    private StatusCentral statusCentral;
    private LocalDateTime dataHora;
    private InformacoesEnergia informacoesEnergia;
    private List<Teclado> teclados;
    private List<Receptor> receptores;
    private Bateria bateria;
    private InformacoesSirene informacoesSirene;
    private boolean corteDeLinhaTelefonica;
    private boolean falhaAoComunicarEvento;
    private List<Pgm> pgms;
    private String checksum;

    public StatusParcial(TipoPacote tipo) {
        super(tipo);
    }

    public void setInformacoesZonas(InformacoesZonas informacoesZonas) {
        this.informacoesZonas = informacoesZonas;
    }

    public void setModeloCentral(ModeloCentral modeloCentral) {
        this.modeloCentral = modeloCentral;
    }

    public void setVersaoFirmware(String versaoFirmware) {
        this.versaoFirmware = versaoFirmware;
    }

    public void setStatusParticao(StatusParticao statusParticao) {
        this.statusParticao = statusParticao;
    }

    public void setParticoes(List<Particao> particoes) {
        this.particoes = particoes;
    }

    public void setStatusCentral(StatusCentral statusCentral) {
        this.statusCentral = statusCentral;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setInformacoesEnergia(InformacoesEnergia informacoesEnergia) {
        this.informacoesEnergia = informacoesEnergia;
    }

    public void setTeclados(List<Teclado> teclados) {
        this.teclados = teclados;
    }

    public void setReceptores(List<Receptor> receptores) {
        this.receptores = receptores;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    public void setInformacoesSirene(InformacoesSirene informacoesSirene) {
        this.informacoesSirene = informacoesSirene;
    }

    public void setCorteDeLinhaTelefonica(boolean corteDeLinhaTelefonica) {
        this.corteDeLinhaTelefonica = corteDeLinhaTelefonica;
    }

    public void setFalhaAoComunicarEvento(boolean falhaAoComunicarEvento) {
        this.falhaAoComunicarEvento = falhaAoComunicarEvento;
    }

    public void setPgms(List<Pgm> pgms) {
        this.pgms = pgms;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
