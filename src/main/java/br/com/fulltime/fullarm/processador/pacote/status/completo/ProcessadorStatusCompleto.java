package br.com.fulltime.fullarm.processador.pacote.status.completo;

import br.com.fulltime.fullarm.constantes.ModeloCentral;
import br.com.fulltime.fullarm.constantes.StatusParticao;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import br.com.fulltime.fullarm.modelo.status.*;
import br.com.fulltime.fullarm.modelo.status.completo.StatusCompleto;
import br.com.fulltime.fullarm.processador.pacote.ProcessadorPacoteFrameLongo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorStatusCompleto implements ProcessadorPacoteFrameLongo {
    private final ParserInformacoesStatusCompleto parserInformacoesStatusCompleto;

    public ProcessadorStatusCompleto(ParserInformacoesStatusCompleto parserInformacoesStatusCompleto) {
        this.parserInformacoesStatusCompleto = parserInformacoesStatusCompleto;
    }

    @Override
    public PacoteGenerico processar(String hexString) {
        List<String> bytes = particionarBytes(hexString);
        StatusCompleto status = new StatusCompleto();
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
                parserInformacoesStatusCompleto.buscarInformacoesZonas(bytesZonasAbertas, bytesZonasVioladas,
                        bytesZonasAnuladas, bytesZonasComTamper, bytesZonasComCurtoCircuito, bytesZonasStatusBateria);
        System.out.println("Adicionando Informações das zonas: " + informacoesZonas);
        statusCompleto.setInformacoesZonas(informacoesZonas);

        ModeloCentral modeloDaCentral = parserInformacoesStatusCompleto.buscarModeloDaCentral(bytesStatus.get(24));
        System.out.println("Adicionando Modelo da Central: " + modeloDaCentral);
        statusCompleto.setModeloCentral(modeloDaCentral);

        String versaoFirmware = parserInformacoesStatusCompleto.buscarVersaoFirmware(bytesStatus.get(25));
        System.out.println("Adicionando Versão do Firmware: " + versaoFirmware);
        statusCompleto.setVersaoFirmware(versaoFirmware);

        StatusParticao statusParticao = parserInformacoesStatusCompleto.buscarStatusParticao(bytesStatus.get(26));
        System.out.println("Adicionando Status de Partição: " + statusParticao);
        statusCompleto.setStatusParticao(statusParticao);

        String bytesParticoes = String.join("", bytesStatus.subList(27, 29));
        List<Particao> particoes = parserInformacoesStatusCompleto.buscarParticoes(bytesParticoes);
        System.out.println("Adicionando Partições: " + particoes);
        statusCompleto.setParticoes(particoes);

        InformacoesCentral informacoesCentral = parserInformacoesStatusCompleto.buscarFuncionamentoDaCentral(bytesStatus.get(29));
        System.out.println("Adicionando Status da Central: " + informacoesCentral);
        statusCompleto.setStatusCentral(informacoesCentral);

        LocalDateTime dataHora = parserInformacoesStatusCompleto.buscarDataEHora(bytesStatus.subList(30, 35));
        System.out.println("Adicionando Data e Hora: " + dataHora);
        statusCompleto.setDataHora(dataHora);

        InformacoesEnergia informacoesEnergia = parserInformacoesStatusCompleto.buscarStatusEnergia(bytesStatus.get(35));
        System.out.println("Adicionando Informações de Energia: " + informacoesEnergia);
        statusCompleto.setInformacoesEnergia(informacoesEnergia);

        String byteProblemaTeclados = bytesStatus.get(36);
        String byteTamperTeclados = bytesStatus.get(41);
        List<Teclado> teclados = parserInformacoesStatusCompleto.buscarTeclados(byteProblemaTeclados, byteTamperTeclados);
        System.out.println("Adicionando Teclados: " + teclados);
        statusCompleto.setTeclados(teclados);

        String byteProblemaReceptores = bytesStatus.get(36);
        List<Receptor> receptores = parserInformacoesStatusCompleto.buscarReceptores(byteProblemaReceptores);
        System.out.println("Adicionando Receptores: " + receptores);
        statusCompleto.setReceptores(receptores);

        Bateria bateria = parserInformacoesStatusCompleto.buscarBateria(bytesStatus.get(40));
        System.out.println("Adicionando Informações da Bateria: " + bateria);
        statusCompleto.setBateria(bateria);

        List<Expansor> expansoresPgm = parserInformacoesStatusCompleto.buscarExpansoresPgm(bytesStatus.get(37));
        System.out.println("Adicionando Expansores de Pgm" + expansoresPgm);
        statusCompleto.setExpansoresPgm(expansoresPgm);

        String nibbleExpansoresByte1 = String.valueOf(bytesStatus.get(37).charAt(1));
        String nibbleExpansoresByte2 = String.valueOf(bytesStatus.get(38).charAt(0));
        List<Expansor> expansoresZonas =
                parserInformacoesStatusCompleto.buscarExpansoresZonas(nibbleExpansoresByte1, nibbleExpansoresByte2);
        System.out.println("Adicionando Expansores de Zona: " + expansoresZonas);
        statusCompleto.setExpansoresZonas(expansoresZonas);

        String byteSireneLigada = bytesStatus.get(45);
        String byteInfoSirene = bytesStatus.get(42);
        InformacoesSirene informacoesSirene = parserInformacoesStatusCompleto.buscarInformacoesSirene(byteSireneLigada, byteInfoSirene);
        System.out.println("Adicionando Informações da Sirene: " + informacoesSirene);
        statusCompleto.setInformacoesSirene(informacoesSirene);

        List<Boolean> problemasComunicacao = parserInformacoesStatusCompleto.buscarBits(bytesStatus.get(42));
        System.out.println("Adicionando Informações de Problemas de Comunicação: Corte de linha telefônica=" + problemasComunicacao.get(2) + ", Falha ao comunicar evento=" + problemasComunicacao.get(3));
        statusCompleto.setCorteDeLinhaTelefonica(problemasComunicacao.get(2));
        statusCompleto.setFalhaAoComunicarEvento(problemasComunicacao.get(3));

        String bytePgm1 = bytesStatus.get(45);
        String bytesPgm2 = String.join("", bytesStatus.subList(52, 54));
        List<Pgm> pgms = parserInformacoesStatusCompleto.buscarPgms(bytePgm1, bytesPgm2);
        System.out.println("Adicionando Pgms: " + pgms);
        statusCompleto.setPgms(pgms);

        String checksum = bytes.get((bytes.size() - 1));
        System.out.println("Adicionando checksum: " + checksum);
        statusCompleto.setChecksum(checksum);
        System.out.println("===================================================================================");
    }
}
