package br.com.fulltime.fullarm.infra.processor.nack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NackProcessorTest {
    private NackProcessor instance;

    @BeforeEach
    public void setup() {
        instance = new NackProcessorImpl();
    }

    @Test
    @DisplayName("Quando pacote recebido conter mais que um byte deve retornar false")
    public void nackProcessingTestScenario1() {
        String packet = NackProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando byte identificador do pacote não for o identificador de Nack deve retornar false")
    public void nackProcessingTestScenario2() {
        String identifierByte = NackProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando tamanho e byte identificador estiverem corretos deve retornar true")
    public void nackProcessingTestScenario3() {
        String identifierByte = NackProcessorConstants.VALID_NACK_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertTrue(result);
    }
}
