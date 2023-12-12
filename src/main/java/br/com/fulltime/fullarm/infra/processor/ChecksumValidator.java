package br.com.fulltime.fullarm.infra.processor;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ChecksumValidator {
    public boolean checksumIsValid(String hexString) {
        byte[] byteArray = hexStringToByteArray(hexString);
        byte[] byteArrayWithoutChecksum = Arrays.copyOfRange(byteArray, 0, byteArray.length - 1);
        return generateChecksum(byteArrayWithoutChecksum) == byteArray[byteArray.length - 1];
    }

    private byte[] hexStringToByteArray(String hexString) {
        String spacelessString = hexString.replace(" ", "");
        int len = spacelessString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(spacelessString.charAt(i), 16) << 4)
                    + Character.digit(spacelessString.charAt(i + 1), 16));
        }
        return bytes;
    }

    private byte generateChecksum(byte[] byteArray) {
        byte checksum = byteArray[0];
        for (int i = 1; i < byteArray.length; i++) {
            checksum ^= byteArray[i];
        }
        return (byte) ~checksum;
    }
}
