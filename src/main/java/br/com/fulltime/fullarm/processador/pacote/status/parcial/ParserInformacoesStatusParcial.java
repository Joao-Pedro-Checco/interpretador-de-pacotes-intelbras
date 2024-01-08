package br.com.fulltime.fullarm.processador.pacote.status.parcial;

import br.com.fulltime.fullarm.constantes.StatusParticao;
import br.com.fulltime.fullarm.constantes.ModeloCentral;
import br.com.fulltime.fullarm.modelo.status.*;
import br.com.fulltime.fullarm.utils.GerenciadorDeBits;
import br.com.fulltime.fullarm.utils.GerenciadorDeZonas;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

@Service
public class ParserInformacoesStatusParcial {
    public List<Integer> buscarZonasComBit1(List<String> subList) {
        return GerenciadorDeZonas.buscarZonasComBit1(subList);
    }

    public InformacoesZonas buscarInformacoesZonas(List<String> bytesZonasAbertas, List<String> bytesZonasVioladas, List<String> bytesZonasAnuladas, List<String> bytesZonasComTamper, List<String> bytesZonasComCurtoCircuito, List<String> bytesZonasStatusBateria) {
        InformacoesZonas informacoesZonas = new InformacoesZonas();
        informacoesZonas.setZonasAbertas(buscarZonasComBit1(bytesZonasAbertas));
        informacoesZonas.setZonasVioladas(buscarZonasComBit1(bytesZonasVioladas));
        informacoesZonas.setZonasAnuladas(buscarZonasComBit1(bytesZonasAnuladas));
        informacoesZonas.setZonasComTamper(buscarZonasComBit1(bytesZonasComTamper));
        informacoesZonas.setZonasComCurtoCircuito(buscarZonasComBit1(bytesZonasComCurtoCircuito));
        informacoesZonas.setZonasComBateriaBaixaNoSensor(buscarZonasComBit1(bytesZonasStatusBateria));

        return informacoesZonas;
    }

    public ModeloCentral buscarModeloDaCentral(String byteModelo) {
        return ModeloCentral.getByValue(byteModelo);
    }

    public String buscarVersaoFirmware(String byteVersao) {
        return String.format("%s.%s", byteVersao.charAt(0), byteVersao.charAt(1));
    }

    public StatusParticao buscarStatusParticao(String byteStatusParticao) {
        return StatusParticao.getByValue(byteStatusParticao);
    }

    public List<Particao> buscarParticoes(String bytesParticoes) {
        char[] nibblesParticoes = bytesParticoes.toCharArray();
        List<Particao> particoes = new ArrayList<>();

        for (char nibbleParticao : nibblesParticoes) {
            Particao particao = new Particao();
            particao.setAtivada(nibbleParticao == '1');
            particoes.add(particao);
        }

        return particoes;
    }

    public InformacoesCentral buscarFuncionamentoDaCentral(String byteFuncionamento) {
        InformacoesCentral informacoesCentral = new InformacoesCentral();
        List<Boolean> bitsStatusCentral = buscarBits(byteFuncionamento);
        informacoesCentral.setProblemasDetectados(bitsStatusCentral.get(0));
        informacoesCentral.setSireneLigada(bitsStatusCentral.get(1));
        informacoesCentral.setExistemZonasDisparadas(bitsStatusCentral.get(2));
        informacoesCentral.setCentralAtivada(bitsStatusCentral.get(3));

        return informacoesCentral;
    }

    public LocalDateTime buscarDataEHora(List<String> bytesDataHora) {
        int hora = Integer.parseInt(bytesDataHora.get(0), 16);
        int minutos = Integer.parseInt(bytesDataHora.get(1), 16);
        int dia = Integer.parseInt(bytesDataHora.get(2), 16);
        int mes = Integer.parseInt(bytesDataHora.get(3), 16);
        int ano = Integer.parseInt(bytesDataHora.get(4), 16) + 2000;

        return LocalDateTime.of(ano, mes, dia, hora, minutos);
    }

    public Bateria buscarBateria(String byteBateria) {
        BitSet bitsStatusBateria = GerenciadorDeBits.byteHexParaBitSet(byteBateria);
        Bateria bateria = new Bateria();
        bateria.setContornoLigado(bitsStatusBateria.get(0));
        bateria.setNivelUmLigado(bitsStatusBateria.get(1));
        bateria.setNivelDoisLigado(bitsStatusBateria.get(2));
        bateria.setNivelTresLigado(bitsStatusBateria.get(3));
        bateria.setContornoPiscar(bitsStatusBateria.get(4));
        bateria.setNivelUmPiscar(bitsStatusBateria.get(5));
        bateria.setNivelDoisPiscar(bitsStatusBateria.get(6));
        bateria.setNivelTresPiscar(bitsStatusBateria.get(7));

        return bateria;
    }

    public List<Boolean> buscarBits(String byteHex) {
        BitSet bitSet = GerenciadorDeBits.byteHexParaBitSet(byteHex);
        List<Boolean> listaBool = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            listaBool.add(bitSet.get(i));
        }

        return listaBool;
    }

    public InformacoesEnergia buscarStatusEnergia(String byteInformacoesEnergia) {
        InformacoesEnergia informacoesEnergia = new InformacoesEnergia();
        List<Boolean> bitInformacoesEnergia = buscarBits(byteInformacoesEnergia);
        informacoesEnergia.setFaltaDeRedeEletrica(bitInformacoesEnergia.get(0));
        informacoesEnergia.setBateriaBaixa(bitInformacoesEnergia.get(1));
        informacoesEnergia.setBateriaAusenteOuInvertida(bitInformacoesEnergia.get(2));
        informacoesEnergia.setBateriaEmCurtoCircuito(bitInformacoesEnergia.get(3));
        informacoesEnergia.setSobrecargaNaSaidaAuxiliar(bitInformacoesEnergia.get(4));

        return informacoesEnergia;
    }

    public List<Teclado> buscarTeclados(String byteProblemaTeclados, String byteTamperTeclados) {
        List<Teclado> teclados = new ArrayList<>();
        BitSet bitsProblemaTeclados = GerenciadorDeBits.byteHexParaBitSet(byteProblemaTeclados);
        BitSet bitsTamperTeclados = GerenciadorDeBits.byteHexParaBitSet(byteTamperTeclados);
        for (int i = 0; i < 4; i++) {
            Teclado teclado = new Teclado();
            teclado.setProblema(bitsProblemaTeclados.get(i));
            teclado.setProblema(bitsTamperTeclados.get(i + 4));
            teclados.add(teclado);
        }

        return teclados;
    }

    public List<Receptor> buscarReceptores(String byteProblemaReceptores) {
        List<Receptor> receptores = new ArrayList<>();
        BitSet bitsProblemaReceptores = GerenciadorDeBits.byteHexParaBitSet(byteProblemaReceptores);
        for (int i = 0; i < 4; i++) {
            Receptor receptor = new Receptor();
            receptor.setProblema(bitsProblemaReceptores.get(i + 4));
            receptores.add(receptor);
        }

        return receptores;
    }

    public List<Pgm> buscarPgms(String byteInformacaoPgm) {
        List<Pgm> pgms = new ArrayList<>();
        Pgm pgm1 = new Pgm();
        Pgm pgm2 = new Pgm();

        Boolean bitPgm1 = buscarBits(byteInformacaoPgm).get(6);
        pgm1.setLigada(bitPgm1);
        Boolean bitPgm2 = buscarBits(byteInformacaoPgm).get(5);
        pgm2.setLigada(bitPgm2);

        Collections.addAll(pgms, pgm1, pgm2);

        return pgms;
    }

    public InformacoesSirene buscarInformacoesSirene(String byteSireneLigada, String byteInfoSirene) {
        InformacoesSirene informacoesSirene = new InformacoesSirene();
        Boolean bitSireneLigada = buscarBits(byteSireneLigada).get(2);
        Boolean bitCorteFioSirene = buscarBits(byteInfoSirene).get(0);
        Boolean bitCurtoCircuitoSirene = buscarBits(byteInfoSirene).get(1);

        informacoesSirene.setSireneLigada(bitSireneLigada);
        informacoesSirene.setCorteDoFioDaSirene(bitCorteFioSirene);
        informacoesSirene.setCurtoCircuitoNoFioDaSirene(bitCurtoCircuitoSirene);

        return informacoesSirene;
    }
}
