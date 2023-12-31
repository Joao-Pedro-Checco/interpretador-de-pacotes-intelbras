package br.com.fulltime.fullarm.modelo.status.completo;

import br.com.fulltime.fullarm.constantes.StatusParticao;
import br.com.fulltime.fullarm.constantes.TipoProcessador;
import br.com.fulltime.fullarm.constantes.ModeloCentral;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import br.com.fulltime.fullarm.modelo.status.*;

import java.time.LocalDateTime;
import java.util.List;

public class StatusCompleto extends PacoteGenerico {
    private InformacoesZonas informacoesZonas;
    private ModeloCentral modeloCentral;
    private String versaoFirmware;
    private StatusParticao statusParticao;
    private List<Particao> particoes;
    private InformacoesCentral informacoesCentral;
    private LocalDateTime dataHora;
    private InformacoesEnergia informacoesEnergia;
    private List<Teclado> teclados;
    private List<Receptor> receptores;
    private Bateria bateria;
    private List<Expansor> expansoresPgm;
    private List<Expansor> expansoresZonas;
    private InformacoesSirene informacoesSirene;
    private boolean corteDeLinhaTelefonica;
    private boolean falhaAoComunicarEvento;
    private List<Pgm> pgms;
    private String checksum;

    public StatusCompleto() {
        super(TipoProcessador.STATUS_COMPLETO);
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

    public void setStatusCentral(InformacoesCentral informacoesCentral) {
        this.informacoesCentral = informacoesCentral;
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

    public void setExpansoresPgm(List<Expansor> expansoresPgm) {
        this.expansoresPgm = expansoresPgm;
    }

    public void setExpansoresZonas(List<Expansor> expansoresZonas) {
        this.expansoresZonas = expansoresZonas;
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

    @Override
    public String toString() {
        return "StatusCompleto{" +
                "informacoesZonas=" + informacoesZonas +
                ", modeloCentral=" + modeloCentral +
                ", versaoFirmware='" + versaoFirmware + '\'' +
                ", statusParticao=" + statusParticao +
                ", particoes=" + particoes +
                ", statusCentral=" + informacoesCentral +
                ", dataHora=" + dataHora +
                ", informacoesEnergia=" + informacoesEnergia +
                ", teclados=" + teclados +
                ", receptores=" + receptores +
                ", bateria=" + bateria +
                ", expansoresPgm=" + expansoresPgm +
                ", expansoresZonas=" + expansoresZonas +
                ", informacoesSirene=" + informacoesSirene +
                ", corteDeLinhaTelefonica=" + corteDeLinhaTelefonica +
                ", falhaAoComunicarEvento=" + falhaAoComunicarEvento +
                ", pgms=" + pgms +
                ", checksum='" + checksum + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
