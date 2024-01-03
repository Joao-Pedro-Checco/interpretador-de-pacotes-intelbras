package br.com.fulltime.fullarm.processador.comando;

import br.com.fulltime.fullarm.pacote.TipoComando;
import br.com.fulltime.fullarm.processador.zonas.GerenciadorDeZonas;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CriadorSubComando {
    public String createSubComando(TipoComando tipo, List<String> bytes) {
        return switch (tipo) {
            case ATIVACAO_DA_CENTRAL, DESATIVACAO_DA_CENTRAL -> createAtivacao(bytes);
            case BYPASS -> createBypass(bytes);
            case CONTROLE_DE_PGM -> createControlePgm(bytes);
            case PANICO -> createPanico(bytes);
            default -> throw new IllegalArgumentException("Tipo de comando desconhecido: " + tipo);
        };
    }

    private String createAtivacao(List<String> bytes) {
        if (bytes.isEmpty()) return "Todas as partições";
        HashMap<String, String> mapaParticoes = new HashMap<>(){{
            put("41", "A");
            put("42", "B");
            put("43", "C");
            put("44", "D");
        }};
        List<String> particoes = gerarListaFiltrada(bytes, mapaParticoes);
        return "Partições" + particoes;
    }

    private String createBypass(List<String> bytes) {
        List<Integer> zonasAnuladas = GerenciadorDeZonas.buscarZonasComBit1(bytes);
        return "Zonas anuladas" + zonasAnuladas;
    }

    private String createControlePgm(List<String> bytes) {
        String acaoPgm = bytes.get(0).equals("4C") ? "Ligar PGM" : "Desligar PGM";
        List<String> bytesPgms = bytes.subList(1, bytes.size());
        HashMap<String, String> mapaPgms = new HashMap<>(){{
            put("31", "1");
            put("32", "2");
            put("33", "3");
            put("34", "4");
            put("35", "5");
            put("36", "6");
            put("37", "7");
            put("38", "8");
            put("39", "9");
            put("3A", "10");
            put("3B", "11");
            put("3C", "12");
            put("3D", "13");
            put("3E", "14");
            put("3F", "15");
            put("40", "16");
            put("41", "17");
            put("42", "18");
            put("43", "19");
        }};
        String pgms = gerarListaFiltrada(bytesPgms, mapaPgms).toString();
        return String.format("%s: PGM's%s", acaoPgm, pgms);
    }

    private String createPanico(List<String> bytes) {
        String _byte = bytes.get(0);
        return switch (_byte) {
            case "00" -> "Pânico silencioso";
            case "01" -> "Pânico audível";
            case "02" -> "Emergência médica";
            case "03" -> "Incêndio";
            default -> throw new IllegalArgumentException("Byte de sub comando inválido: " + _byte);
        };
    }

    private List<String> gerarListaFiltrada(List<String> lista, HashMap<String, String> mapa) {
        return lista.stream().map(mapa::get).toList();
    }
}
