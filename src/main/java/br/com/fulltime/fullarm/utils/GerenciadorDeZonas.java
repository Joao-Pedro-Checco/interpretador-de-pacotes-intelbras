package br.com.fulltime.fullarm.utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class GerenciadorDeZonas {
    public static List<Integer> getZonasAnuladas(List<String> bytes) {
        int offset = 1;
        List<Integer> zonasAnuladas = new ArrayList<>();
        for (String aByte : bytes) {
            BitSet zonas = GerenciadorDeBits.byteHexParaBitSet(aByte);
            zonasAnuladas.addAll(zonasComOffset(zonas, offset));
            offset += 8;
        }

        return zonasAnuladas;
    }

    private static List<Integer> zonasComOffset(BitSet zonas, int offset) {
        int index = 0;
        List<Integer> zonasComOffset = new ArrayList<>();
        while (zonas.nextSetBit(index) > -1) {
            if (zonas.get(index)) zonasComOffset.add(index + offset);
            index++;
        }

        return zonasComOffset;
    }
}
