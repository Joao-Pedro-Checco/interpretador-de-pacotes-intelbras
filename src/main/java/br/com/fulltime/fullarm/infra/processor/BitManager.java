package br.com.fulltime.fullarm.infra.processor;

import java.util.BitSet;

public class BitManager {
    public static BitSet convertHexByteToBitSet(String byteHex) {
        int decimalValue = Integer.parseInt(byteHex, 16);
        return BitSet.valueOf(new long[]{decimalValue});
    }
}
