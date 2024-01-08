package br.com.fulltime.fullarm.processador.status.parcial;

import br.com.fulltime.fullarm.constantes.ModeloCentral;
import br.com.fulltime.fullarm.constantes.StatusParticao;
import br.com.fulltime.fullarm.modelo.*;
import br.com.fulltime.fullarm.modelo.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.modelo.pacote.status.parcial.StatusParcial;
import br.com.fulltime.fullarm.processador.ProcessadorPacoteFrameLongo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorStatusParcial implements ProcessadorPacoteFrameLongo {
    private final ParserInformacoesStatusParcial parserInformacoesStatusParcial;

    public ProcessadorStatusParcial(ParserInformacoesStatusParcial parserInformacoesStatusParcial) {
        this.parserInformacoesStatusParcial = parserInformacoesStatusParcial;
    }

    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);
        StatusParcial status = new StatusParcial();
        montarStatus(bytes, status);
        return status;
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private void montarStatus(List<String> bytes, StatusParcial statusParcial) {
        System.out.println("Montando status parcial...");
        System.out.println("===================================================================================");
        List<String> bytesStatus = bytes.subList(2, bytes.size() - 1);
        List<String> bytesZonasAbertas = bytesStatus.subList(0, 6);
        List<String> bytesZonasVioladas = bytesStatus.subList(6, 12);
        List<String> bytesZonasAnuladas = bytesStatus.subList(12, 18);
        List<String> bytesZonasComTamper = bytesStatus.subList(33, 35);
        List<String> bytesZonasComCurtoCircuito = bytesStatus.subList(35, 37);
        List<String> bytesZonasStatusBateria = bytesStatus.subList(38, 43);
        InformacoesZonas informacoesZonas =
                parserInformacoesStatusParcial.buscarInformacoesZonas(bytesZonasAbertas, bytesZonasVioladas,
                        bytesZonasAnuladas, bytesZonasComTamper, bytesZonasComCurtoCircuito, bytesZonasStatusBateria);
        System.out.println("Adicionando Informações das zonas: " + informacoesZonas);
        statusParcial.setInformacoesZonas(informacoesZonas);

        ModeloCentral modeloDaCentral = parserInformacoesStatusParcial.buscarModeloDaCentral(bytesStatus.get(18));
        System.out.println("Adicionando Modelo da Central: " + modeloDaCentral);
        statusParcial.setModeloCentral(modeloDaCentral);

        String versaoFirmware = parserInformacoesStatusParcial.buscarVersaoFirmware(bytesStatus.get(19));
        System.out.println("Adicionando Versão do Firmware: " + versaoFirmware);
        statusParcial.setVersaoFirmware(versaoFirmware);

        StatusParticao statusParticao = parserInformacoesStatusParcial.buscarStatusParticao(bytesStatus.get(20));
        System.out.println("Adicionando Status de partição: " + statusParticao);
        statusParcial.setStatusParticao(statusParticao);

        String byteParticoes = bytesStatus.get(21);
        List<Particao> particoes = parserInformacoesStatusParcial.buscarParticoes(byteParticoes);
        System.out.println("Adicionando Partições: " + particoes);
        statusParcial.setParticoes(particoes);

        InformacoesCentral informacoesCentral = parserInformacoesStatusParcial.buscarFuncionamentoDaCentral(bytesStatus.get(22));
        System.out.println("Adicionando Informações da Central: " + informacoesCentral);
        statusParcial.setStatusCentral(informacoesCentral);

        LocalDateTime dataHora = parserInformacoesStatusParcial.buscarDataEHora(bytesStatus.subList(23, 28));
        System.out.println("Adicionando Data e Hora: " + dataHora);
        statusParcial.setDataHora(dataHora);

        InformacoesEnergia informacoesEnergia = parserInformacoesStatusParcial.buscarStatusEnergia(bytesStatus.get(28));
        System.out.println("Adicionando Informações de energia: " + informacoesEnergia);
        statusParcial.setInformacoesEnergia(informacoesEnergia);

        String byteProblemaTeclados = bytesStatus.get(29);
        String byteTamperTeclados = bytesStatus.get(31);
        List<Teclado> teclados = parserInformacoesStatusParcial.buscarTeclados(byteProblemaTeclados, byteTamperTeclados);
        System.out.println("Adicionando Teclados: " + teclados);
        statusParcial.setTeclados(teclados);

        String byteProblemaReceptores = bytesStatus.get(29);
        List<Receptor> receptores = parserInformacoesStatusParcial.buscarReceptores(byteProblemaReceptores);
        System.out.println("Adicionando Receptores: " + receptores);
        statusParcial.setReceptores(receptores);

        Bateria bateria = parserInformacoesStatusParcial.buscarBateria(bytesStatus.get(30));
        System.out.println("Adicionando Informações da Bateria: " + bateria);
        statusParcial.setBateria(bateria);

        String byteSireneLigada = bytesStatus.get(37);
        String byteInfoSirene = bytesStatus.get(32);
        InformacoesSirene informacoesSirene =
                parserInformacoesStatusParcial.buscarInformacoesSirene(byteSireneLigada, byteInfoSirene);
        System.out.println("Adicionando Informações da Sirene: " + informacoesSirene);
        statusParcial.setInformacoesSirene(informacoesSirene);

        List<Boolean> problemasComunicacao = parserInformacoesStatusParcial.buscarBits(bytesStatus.get(32));
        System.out.println("Adicionando Informações de Problemas de Comunicação: Corte de linha telefônica=" + problemasComunicacao.get(2) + ", Falha ao comunicar evento=" + problemasComunicacao.get(3));
        statusParcial.setCorteDeLinhaTelefonica(problemasComunicacao.get(2));
        statusParcial.setFalhaAoComunicarEvento(problemasComunicacao.get(3));

        String byteInformacaoPgm = bytesStatus.get(37);
        List<Pgm> pgms = parserInformacoesStatusParcial.buscarPgms(byteInformacaoPgm);
        System.out.println("Adicionando Pmgs: " + pgms);
        statusParcial.setPgms(pgms);

        String checksum = bytes.get((bytes.size() - 1));
        System.out.println("Adicionando checksum: " + checksum);
        statusParcial.setChecksum(checksum);
        System.out.println("===================================================================================");
    }
}
