package br.com.fulltime.fullarm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidadorHexString {
    public static void validar(String hexString) {
        if (!byteIdentificadorEhValido(hexString)) try {
            throw new HexStringInvalidaException("Byte identificador inválido para o pacote: " + hexString);
        } catch (HexStringInvalidaException e) {
            throw new RuntimeException(e);
        }

        if (hexString.split(" ").length > 1) {
            byte[] byteArray = hexStringParaByteArray(hexString.replace(" ", ""));
            if (!checkSumEhValida(byteArray)) try {
                throw new HexStringInvalidaException("Checksum inválido para o pacote: " + hexString);
            } catch (HexStringInvalidaException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean byteIdentificadorEhValido(String hexString) {
        List<String> bytesValidos = new ArrayList<>();
        String[] hexArray = hexString.split(" ");
        if (hexArray.length > 1) {
            Collections.addAll(bytesValidos, "B0", "94", "E9");
            return bytesValidos.contains(hexArray[1]);
        }

        Collections.addAll(bytesValidos, "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "FE", "F7");
        return bytesValidos.contains(hexString);
    }

    private static byte[] hexStringParaByteArray(String hexString) {
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    private static boolean checkSumEhValida(byte[] byteArray) {
        byte[] byteArraySemChecksum = Arrays.copyOfRange(byteArray, 0, byteArray.length - 1);
        return gerarCheckSum(byteArraySemChecksum) == byteArray[byteArray.length - 1];
    }

    private static byte gerarCheckSum(byte[] byteArray) {
        byte checkSum = byteArray[0];
        for (int i = 1; i < byteArray.length; i++) {
            checkSum ^= byteArray[i];
        }
        return (byte) ~checkSum;
    }
}
