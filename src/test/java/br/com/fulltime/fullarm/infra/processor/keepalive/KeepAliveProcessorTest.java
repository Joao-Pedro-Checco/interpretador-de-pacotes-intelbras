package br.com.fulltime.fullarm.infra.processor.keepalive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KeepAliveProcessorTest {
    private KeepAliveProcessor instance;

    @BeforeEach
    public void setup() {
        instance = new KeepAliveProcessorImpl();
    }

    @Test
    @DisplayName("Quando pacote recebido conter mais que um byte deve retornar false")
    public void keepAliveProcessingTestScenario1() {
        String packet = KeepAliveProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando byte identificador do pacote não for o identificador de Keep-Alive deve retornar false")
    public void keepAliveProcessingTestScenario2() {
        String identifierByte = KeepAliveProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando byte identificador do pacote for o identificador de Keep-Alive deve retornar true")
    public void keepAliveProcessingTestScenario3() {
        String identifierByte = KeepAliveProcessorConstants.VALID_KEEP_ALIVE_PACKAGE;

        boolean result = instance.canProcess(identifierByte);

        Assertions.assertTrue(result);
    }
}
