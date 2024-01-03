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
        statusCompleto.setInformacoesZonas(informacoesZonas);

        ModeloCentral modeloDaCentral = parserInformacoesStatusParcial.buscarModeloDaCentral(bytesStatus.get(24));
        statusCompleto.setModeloCentral(modeloDaCentral);

        String versaoFirmware = parserInformacoesStatusParcial.buscarVersaoFirmware(bytesStatus.get(25));
        statusCompleto.setVersaoFirmware(versaoFirmware);

        StatusParticao statusParticao = parserInformacoesStatusParcial.buscarStatusParticao(bytesStatus.get(26));
        statusCompleto.setStatusParticao(statusParticao);

        String bytesParticoes = String.join("", bytesStatus.subList(27, 29));
        List<Particao> particoes = parserInformacoesStatusParcial.buscarParticoes(bytesParticoes);
        statusCompleto.setParticoes(particoes);

        StatusCentral statusCentral = parserInformacoesStatusParcial.buscarFuncionamentoDaCentral(bytesStatus.get(29));
        statusCompleto.setStatusCentral(statusCentral);

        LocalDateTime dataHora = parserInformacoesStatusParcial.buscarDataEHora(bytesStatus.subList(30, 35));
        statusCompleto.setDataHora(dataHora);

        InformacoesEnergia informacoesEnergia = parserInformacoesStatusParcial.buscarStatusEnergia(bytesStatus.get(35));
        statusCompleto.setInformacoesEnergia(informacoesEnergia);

        String byteProblemaTeclados = bytesStatus.get(36);
        String byteTamperTeclados = bytesStatus.get(41);
        List<Teclado> teclados = parserInformacoesStatusParcial.buscarTeclados(byteProblemaTeclados, byteTamperTeclados);
        statusCompleto.setTeclados(teclados);

        String byteProblemaReceptores = bytesStatus.get(36);
        List<Receptor> receptores = parserInformacoesStatusParcial.buscarReceptores(byteProblemaReceptores);
        statusCompleto.setReceptores(receptores);

        List<Expansor> expansoresPgm =
                parserInformacoesStatusCompleto.buscarExpansoresPgm(bytesStatus.get(37));
        statusCompleto.setExpansoresPgm(expansoresPgm);

        String nibbleExpansoresByte1 = String.valueOf(bytesStatus.get(37).charAt(1));
        String nibbleExpansoresByte2 = String.valueOf(bytesStatus.get(38).charAt(0));
        List<Expansor> expansoresZonas =
                parserInformacoesStatusCompleto.buscarExpansoresZonas(nibbleExpansoresByte1, nibbleExpansoresByte2);
        statusCompleto.setExpansoresZonas(expansoresZonas);

        Bateria bateria = parserInformacoesStatusParcial.buscarBateria(bytesStatus.get(40));
        statusCompleto.setBateria(bateria);

        String byteSireneLigada = bytesStatus.get(45);
        String byteInfoSirene = bytesStatus.get(42);
        InformacoesSirene informacoesSirene = parserInformacoesStatusParcial.buscarInformacoesSirene(byteSireneLigada, byteInfoSirene);
        statusCompleto.setInformacoesSirene(informacoesSirene);

        List<Boolean> problemasComunicacao = parserInformacoesStatusParcial.buscarBits(bytesStatus.get(42));
        statusCompleto.setCorteDeLinhaTelefonica(problemasComunicacao.get(2));
        statusCompleto.setFalhaAoComunicarEvento(problemasComunicacao.get(3));

        String bytePgm1 = bytesStatus.get(45);
        String bytesPgm2 = String.join("", bytesStatus.subList(52, 54));
        List<Pgm> pgms = parserInformacoesStatusCompleto.buscarPgms(bytePgm1, bytesPgm2);
        statusCompleto.setPgms(pgms);

        statusCompleto.setChecksum(bytes.get(bytes.size() - 1));
    }
}
