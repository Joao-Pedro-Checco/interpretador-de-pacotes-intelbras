package br.com.fulltime.fullarm.app;

import br.com.fulltime.fullarm.core.PackageUserInput;
import org.springframework.stereotype.Component;

@Component
public class UserInputFormatter {
    public PackageUserInput format(String hexString) {
        boolean hexStringIsShortFrame = hexString.length() == 2;
        if (hexStringIsShortFrame) {
            return new PackageUserInput(hexString);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String hexPair = hexString.substring(i, i + 2);
            stringBuilder.append(hexPair).append(" ");
        }

        String finalString = stringBuilder.toString();
        return new PackageUserInput(finalString);
    }
}
