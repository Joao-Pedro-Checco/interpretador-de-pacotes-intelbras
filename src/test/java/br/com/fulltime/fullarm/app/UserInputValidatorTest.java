package br.com.fulltime.fullarm.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInputValidatorTest {
    private UserInputValidator instance;

    @BeforeEach
    public void setup() {
        instance = new UserInputValidator();
    }

    @Test
    @DisplayName("Quando for inserido valor vazio deve retornar false")
    public void emptyInputTestScenario1() {
        String userInput = UserInputValidatorConstants.EMPTY_USER_INPUT;

        boolean result = instance.isValid(userInput);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando for inserido valor não vazio deve retornar true")
    public void emptyInputTestScenario2() {
        String userInput = UserInputValidatorConstants.NOT_EMPTY_USER_INPUT;

        boolean result = instance.isValid(userInput);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Quando for inserido valor com número ímpar de nibbles deve retornar false")
    public void nibbleAmountTestScenario1() {
        String userInput = UserInputValidatorConstants.PACKAGE_WITH_ODD_NUMBER_OF_NIBBLES;

        boolean result = instance.isValid(userInput);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando for inserido valor com número par de nibbles deve retornar true")
    public void nibbleAmoutTestScenario2() {
        String userInput = UserInputValidatorConstants.PACKAGE_WITH_EVEN_NUMBER_OF_NIBBLES;

        boolean result = instance.isValid(userInput);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Quando forem inserido valores que não são hexadecimais deve retornar false")
    public void inputIsHexadecimalTestScenario1() {
        String userInput = UserInputValidatorConstants.PACKAGE_WITH_NON_HEXADECIMAL_VALUES;

        boolean result = instance.isValid(userInput);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando for inserido valor hexadecimal deve retornar true")
    public void inputIsHexadecimalTestScenario2() {
        String userInput = UserInputValidatorConstants.PACKAGE_WITH_HEXADECIMAL_VALUES;

        boolean result = instance.isValid(userInput);

        Assertions.assertTrue(result);
    }
}
