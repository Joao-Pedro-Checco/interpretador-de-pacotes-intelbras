package br.com.fulltime.fullarm.infra.processor.ack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AckProcessorTest {
    private AckProcessor instance;

    @BeforeEach
    public void setup() {
        instance = new AckProcessorImpl();
    }

    @Test
    @DisplayName("Quando pacote recebido conter mais que um byte deve retornar false")
    public void ackProcessingTestScenario1() {
        String packet = AckProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando byte identificador não for o identificador de Ack deve retornar false")
    public void ackProcessingTestScenario2() {
        String identifierByte = AckProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando tamanho do pacote e byte identificador forem corretos deve retornar true")
    public void ackProcessingTestScenario3() {
        String identifierByte = AckProcessorConstants.VALID_ACK_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertTrue(result);
    }
}
