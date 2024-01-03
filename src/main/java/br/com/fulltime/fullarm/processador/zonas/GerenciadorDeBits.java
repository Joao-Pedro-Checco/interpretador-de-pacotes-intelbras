package br.com.fulltime.fullarm.processador.zonas;

import java.util.BitSet;

public class GerenciadorDeBits {
    public static BitSet byteHexParaBitSet(String byteHex) {
        int valorDecimal = Integer.parseInt(byteHex, 16);
        return BitSet.valueOf(new long[]{valorDecimal});
    }
}
