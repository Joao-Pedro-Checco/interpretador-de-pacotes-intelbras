package br.com.fulltime.fullarm.utils;

public class FormatadorHexStr {
    public static String formatar(String hexString) {
        if (hexString.contains(" ")) {
            hexString = hexString.replace(" ", "");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String parHex = hexString.substring(i, i + 2);
            stringBuilder.append(parHex).append(" ");
        }

        return stringBuilder.toString().trim().toUpperCase();
    }

    public static String hexParaAscii(String hexString) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String str = hexString.substring(i, i + 2);
            output.append((char)Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
