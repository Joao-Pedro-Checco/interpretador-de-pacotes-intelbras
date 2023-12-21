package br.com.fulltime.fullarm.utils;

import java.util.BitSet;

public class GerenciadorDeBits {
    public static BitSet byteHexParaBitSet(String byteHex) {
        int valorDecimal = Integer.parseInt(byteHex, 16);
        return BitSet.valueOf(new long[]{valorDecimal});
    }
}
