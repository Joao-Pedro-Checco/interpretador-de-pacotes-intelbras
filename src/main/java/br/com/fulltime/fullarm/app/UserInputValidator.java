package br.com.fulltime.fullarm.app;

import org.springframework.stereotype.Component;

@Component
public class UserInputValidator {
    public boolean isValid(String userInput) {
        if (isEmptyString(userInput)) {
            return false;
        }

        if (!isNumberOfNibblesEven(userInput)) {
            return false;
        }

        if (!isHexadecimal(userInput)) {
            return false;
        }

        return true;
    }

    private boolean isNumberOfNibblesEven(String userInput) {
        return userInput.length() % 2 == 0;
    }

    private boolean isEmptyString(String userInput) {
        return userInput.isEmpty();
    }

    private boolean isHexadecimal(String userInput) {
        char[] template = "0123456789ABCDEF".toCharArray();
        char[] charArray = userInput.toCharArray();
        for (char character : charArray) {
            if (!isCharInTemplate(character, template)) return false;
        }

        return true;
    }

    private boolean isCharInTemplate(char character, char[] template) {
        for (char digit : template) {
            if (character == digit) return true;
        }

        return false;
    }
}
