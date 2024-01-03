package br.com.fulltime.fullarm.processador.status;

import br.com.fulltime.fullarm.modelo.*;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.StatusParcial;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import br.com.fulltime.fullarm.pacote.parser.ParserInformacoesStatusParcial;
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
        TipoPacote tipoPacote = TipoPacote.STATUS_PARCIAL;
        StatusParcial status = new StatusParcial(tipoPacote);
        montarStatus(bytes, status);
        return status;
    }

    @Override
    public List<String> particionarBytes(String hexString) {
        return Arrays.asList(hexString.split(" "));
    }

    private void montarStatus(List<String> bytes, StatusParcial statusParcial) {
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
        statusParcial.setInformacoesZonas(informacoesZonas);

        ModeloCentral modeloDaCentral = parserInformacoesStatusParcial.buscarModeloDaCentral(bytesStatus.get(18));
        statusParcial.setModeloCentral(modeloDaCentral);

        String versaoFirmware = parserInformacoesStatusParcial.buscarVersaoFirmware(bytesStatus.get(19));
        statusParcial.setVersaoFirmware(versaoFirmware);

        StatusParticao statusParticao = parserInformacoesStatusParcial.buscarStatusParticao(bytesStatus.get(20));
        statusParcial.setStatusParticao(statusParticao);

        String byteParticoes = bytesStatus.get(21);
        List<Particao> particoes = parserInformacoesStatusParcial.buscarParticoes(byteParticoes);
        statusParcial.setParticoes(particoes);

        StatusCentral statusCentral = parserInformacoesStatusParcial.buscarFuncionamentoDaCentral(bytesStatus.get(22));
        statusParcial.setStatusCentral(statusCentral);

        LocalDateTime dataHora = parserInformacoesStatusParcial.buscarDataEHora(bytesStatus.subList(23, 28));
        statusParcial.setDataHora(dataHora);

        InformacoesEnergia informacoesEnergia = parserInformacoesStatusParcial.buscarStatusEnergia(bytesStatus.get(28));
        statusParcial.setInformacoesEnergia(informacoesEnergia);

        String byteProblemaTeclados = bytesStatus.get(29);
        String byteTamperTeclados = bytesStatus.get(31);
        List<Teclado> teclados = parserInformacoesStatusParcial.buscarTeclados(byteProblemaTeclados, byteTamperTeclados);
        statusParcial.setTeclados(teclados);

        String byteProblemaReceptores = bytesStatus.get(29);
        List<Receptor> receptores = parserInformacoesStatusParcial.buscarReceptores(byteProblemaReceptores);
        statusParcial.setReceptores(receptores);

        Bateria bateria = parserInformacoesStatusParcial.buscarBateria(bytesStatus.get(30));
        statusParcial.setBateria(bateria);

        String byteSireneLigada = bytesStatus.get(37);
        String byteInfoSirene = bytesStatus.get(32);
        InformacoesSirene informacoesSirene =
                parserInformacoesStatusParcial.buscarInformacoesSirene(byteSireneLigada, byteInfoSirene);
        statusParcial.setInformacoesSirene(informacoesSirene);

        List<Boolean> problemasComunicacao = parserInformacoesStatusParcial.buscarBits(bytesStatus.get(32));
        statusParcial.setCorteDeLinhaTelefonica(problemasComunicacao.get(2));
        statusParcial.setFalhaAoComunicarEvento(problemasComunicacao.get(3));

        String byteInformacaoPgm = bytesStatus.get(37);
        List<Pgm> pgms = parserInformacoesStatusParcial.buscarPgms(byteInformacaoPgm);
        statusParcial.setPgms(pgms);

        statusParcial.setChecksum(bytes.get(bytes.size() - 1));
    }
}
