package br.com.fulltime.fullarm.pacote.parser;

import br.com.fulltime.fullarm.modelo.Expansor;
import br.com.fulltime.fullarm.modelo.Pgm;
import br.com.fulltime.fullarm.processador.zonas.GerenciadorDeBits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

@Service
public class ParserInformacoesStatusCompleto {
    public List<Expansor> buscarExpansoresPgm(String byteExpansoresPgm) {
        List<Expansor> expansores = new ArrayList<>();
        BitSet bitsExpansores = GerenciadorDeBits.byteHexParaBitSet(byteExpansoresPgm);
        for (int i = 0; i < 4; i++) {
            Expansor expansor = new Expansor();
            expansor.setProblema(bitsExpansores.get(i));
            expansores.add(expansor);
        }

        return expansores;
    }

    public List<Expansor> buscarExpansoresZonas(String nibble1, String nibble2) {
        List<Expansor> expansores = new ArrayList<>();
        BitSet bitsExpansores = GerenciadorDeBits.byteHexParaBitSet(nibble1);
        BitSet bitsExpansores2 = GerenciadorDeBits.byteHexParaBitSet(nibble2);
        bitsExpansores.or(bitsExpansores2);

        for (int i = 0; i < 6; i++) {
            Expansor expansor = new Expansor();
            expansor.setProblema(bitsExpansores.get(i));
            expansores.add(expansor);
        }

        return expansores;
    }

    public List<Pgm> buscarPgms(String bytePgm1, String bytesPgm2) {
        List<Pgm> pgms = new ArrayList<>();
        List<Boolean> bits = new ArrayList<>();
        Boolean bitPgm1 = buscarBits(bytePgm1).get(6);
        Boolean bitPgm2 = buscarBits(bytePgm1).get(5);
        Boolean bitPgm3 = buscarBits(bytePgm1).get(4);
        Collections.addAll(bits, bitPgm1, bitPgm2, bitPgm3);
        bits.addAll(buscarBits(bytesPgm2));

        for (Boolean bit : bits) {
            Pgm pgm = new Pgm();
            pgm.setLigada(bit);
            pgms.add(pgm);
        }

        return pgms;
    }

    private List<Boolean> buscarBits(String _byte) {
        BitSet bitSet = GerenciadorDeBits.byteHexParaBitSet(_byte);
        List<Boolean> listaBool = new ArrayList<>();

        if (bitSet.isEmpty()) {
            return new ArrayList<>(Collections.nCopies(8, false));
        }

        for (int i = 0; i < bitSet.length(); i++) {
            listaBool.add(bitSet.get(i));
        }

        return listaBool;
    }
}
