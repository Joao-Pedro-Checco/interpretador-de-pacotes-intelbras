package br.com.fulltime.fullarm.processador.zonas;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class GerenciadorDeZonas {
    public static List<Integer> buscarZonasComBit1(List<String> bytes) {
        int offset = 1;
        List<Integer> zonasComBit1 = new ArrayList<>();
        for (String aByte : bytes) {
            BitSet zonas = GerenciadorDeBits.byteHexParaBitSet(aByte);
            zonasComBit1.addAll(buscarZonasComOffset(zonas, offset));
            offset += 8;
        }

        return zonasComBit1;
    }

    private static List<Integer> buscarZonasComOffset(BitSet zonas, int offset) {
        int index = 0;
        List<Integer> zonasComOffset = new ArrayList<>();
        while (zonas.nextSetBit(index) > -1) {
            if (zonas.get(index)) zonasComOffset.add(index + offset);
            index++;
        }

        return zonasComOffset;
    }
}
