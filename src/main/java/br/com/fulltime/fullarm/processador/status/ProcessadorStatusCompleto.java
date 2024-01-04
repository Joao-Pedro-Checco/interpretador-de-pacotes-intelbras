package br.com.fulltime.fullarm.processador.status;

import br.com.fulltime.fullarm.modelo.*;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.StatusCompleto;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import br.com.fulltime.fullarm.pacote.parser.ParserInformacoesStatusCompleto;
import br.com.fulltime.fullarm.pacote.parser.ParserInformacoesStatusParcial;
import br.com.fulltime.fullarm.processador.ProcessadorPacoteFrameLongo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorStatusCompleto implements ProcessadorPacoteFrameLongo {
    private final ParserInformacoesStatusParcial parserInformacoesStatusParcial;
    private final ParserInformacoesStatusCompleto parserInformacoesStatusCompleto;

    public ProcessadorStatusCompleto(ParserInformacoesStatusParcial parserInformacoesStatusParcial, ParserInformacoesStatusCompleto parserInformacoesStatusCompleto) {
        this.parserInformacoesStatusParcial = parserInformacoesStatusParcial;
        this.parserInformacoesStatusCompleto = parserInformacoesStatusCompleto;
    }

    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);
        TipoPacote tipoPacote = TipoPacote.STATUS_COMPLETO;
        StatusCompleto status = new StatusCompleto(tipoPacote);
        montarStatus(bytes, status);
        return status;
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private void montarStatus(List<String> bytes, StatusCompleto statusCompleto) {
        System.out.println("Montando Status Completo...");
        System.out.println("===================================================================================");
        List<String> bytesStatus = bytes.subList(2, bytes.size() - 1);
        List<String> bytesZonasAbertas = bytesStatus.subList(0, 8);
        List<String> bytesZonasVioladas = bytesStatus.subList(7, 17);
        List<String> bytesZonasAnuladas = bytesStatus.subList(16, 24);
        List<String> bytesZonasComTamper = bytesStatus.subList(43, 44);
        List<String> bytesZonasComCurtoCircuito = bytesStatus.subList(44, 45);
        List<String> bytesZonasStatusBateria = bytesStatus.subList(46, 52);
        InformacoesZonas informacoesZonas =
                parserInformacoesStatusParcial.buscarInformacoesZonas(bytesZonasAbertas, bytesZonasVioladas,
                        bytesZonasAnuladas, bytesZonasComTamper, bytesZonasComCurtoCircuito, bytesZonasStatusBateria);
        System.out.println("Adicionando Informações das zonas: " + informacoesZonas);
        statusCompleto.setInformacoesZonas(informacoesZonas);
        System.out.println("===================================================================================");

        ModeloCentral modeloDaCentral = parserInformacoesStatusParcial.buscarModeloDaCentral(bytesStatus.get(24));
        System.out.println("Adicionando Modelo da Central: " + modeloDaCentral);
        statusCompleto.setModeloCentral(modeloDaCentral);
        System.out.println("===================================================================================");

        String versaoFirmware = parserInformacoesStatusParcial.buscarVersaoFirmware(bytesStatus.get(25));
        System.out.println("Adicionando Versão do Firmware: " + versaoFirmware);
        statusCompleto.setVersaoFirmware(versaoFirmware);
        System.out.println("===================================================================================");

        StatusParticao statusParticao = parserInformacoesStatusParcial.buscarStatusParticao(bytesStatus.get(26));
        System.out.println("Adicionando Status de Partição: " + statusParticao);
        statusCompleto.setStatusParticao(statusParticao);
        System.out.println("===================================================================================");

        String bytesParticoes = String.join("", bytesStatus.subList(27, 29));
        List<Particao> particoes = parserInformacoesStatusParcial.buscarParticoes(bytesParticoes);
        System.out.println("Adicionando Partições: " + particoes);
        statusCompleto.setParticoes(particoes);
        System.out.println("===================================================================================");

        InformacoesCentral informacoesCentral = parserInformacoesStatusParcial.buscarFuncionamentoDaCentral(bytesStatus.get(29));
        System.out.println("Adicionando Status da Central: " + informacoesCentral);
        statusCompleto.setStatusCentral(informacoesCentral);
        System.out.println("===================================================================================");

        LocalDateTime dataHora = parserInformacoesStatusParcial.buscarDataEHora(bytesStatus.subList(30, 35));
        System.out.println("Adicionando Data e Hora: " + dataHora);
        statusCompleto.setDataHora(dataHora);
        System.out.println("===================================================================================");

        InformacoesEnergia informacoesEnergia = parserInformacoesStatusParcial.buscarStatusEnergia(bytesStatus.get(35));
        System.out.println("Adicionando Informações de Energia: " + informacoesEnergia);
        statusCompleto.setInformacoesEnergia(informacoesEnergia);
        System.out.println("===================================================================================");

        String byteProblemaTeclados = bytesStatus.get(36);
        String byteTamperTeclados = bytesStatus.get(41);
        List<Teclado> teclados = parserInformacoesStatusParcial.buscarTeclados(byteProblemaTeclados, byteTamperTeclados);
        System.out.println("Adicionando Teclados: " + teclados);
        statusCompleto.setTeclados(teclados);
        System.out.println("===================================================================================");

        String byteProblemaReceptores = bytesStatus.get(36);
        List<Receptor> receptores = parserInformacoesStatusParcial.buscarReceptores(byteProblemaReceptores);
        System.out.println("Adicionando Receptores: " + receptores);
        statusCompleto.setReceptores(receptores);
        System.out.println("===================================================================================");

        Bateria bateria = parserInformacoesStatusParcial.buscarBateria(bytesStatus.get(40));
        System.out.println("Adicionando Informações da Bateria: " + bateria);
        statusCompleto.setBateria(bateria);
        System.out.println("===================================================================================");

        List<Expansor> expansoresPgm = parserInformacoesStatusCompleto.buscarExpansoresPgm(bytesStatus.get(37));
        System.out.println("Adicionando Expansores de Pgm" + expansoresPgm);
        statusCompleto.setExpansoresPgm(expansoresPgm);
        System.out.println("===================================================================================");

        String nibbleExpansoresByte1 = String.valueOf(bytesStatus.get(37).charAt(1));
        String nibbleExpansoresByte2 = String.valueOf(bytesStatus.get(38).charAt(0));
        List<Expansor> expansoresZonas =
                parserInformacoesStatusCompleto.buscarExpansoresZonas(nibbleExpansoresByte1, nibbleExpansoresByte2);
        System.out.println("Adicionando Expansores de Zona: " + expansoresZonas);
        statusCompleto.setExpansoresZonas(expansoresZonas);
        System.out.println("===================================================================================");

        String byteSireneLigada = bytesStatus.get(45);
        String byteInfoSirene = bytesStatus.get(42);
        InformacoesSirene informacoesSirene = parserInformacoesStatusParcial.buscarInformacoesSirene(byteSireneLigada, byteInfoSirene);
        System.out.println("Adicionando Informações da Sirene: " + informacoesSirene);
        statusCompleto.setInformacoesSirene(informacoesSirene);
        System.out.println("===================================================================================");

        List<Boolean> problemasComunicacao = parserInformacoesStatusParcial.buscarBits(bytesStatus.get(42));
        System.out.println("Adicionando Informações de Problemas de Comunicação: Corte de linha telefônica=" + problemasComunicacao.get(2) + ", Falha ao comunicar evento=" + problemasComunicacao.get(3));
        statusCompleto.setCorteDeLinhaTelefonica(problemasComunicacao.get(2));
        statusCompleto.setFalhaAoComunicarEvento(problemasComunicacao.get(3));
        System.out.println("===================================================================================");

        String bytePgm1 = bytesStatus.get(45);
        String bytesPgm2 = String.join("", bytesStatus.subList(52, 54));
        List<Pgm> pgms = parserInformacoesStatusCompleto.buscarPgms(bytePgm1, bytesPgm2);
        System.out.println("Adicionando Pgms: " + pgms);
        statusCompleto.setPgms(pgms);
        System.out.println("===================================================================================");

        String checksum = bytes.get((bytes.size() - 1));
        System.out.println("Adicionando checksum: " + checksum);
        statusCompleto.setChecksum(checksum);
        System.out.println("===================================================================================");
    }
}
