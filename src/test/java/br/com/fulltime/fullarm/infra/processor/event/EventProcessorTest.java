package br.com.fulltime.fullarm.infra.processor.event;

import br.com.fulltime.fullarm.core.message.builder.factory.EventBuilderFactory;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class EventProcessorTest {
    private EventProcessor instance;
    private ChecksumValidator checksumValidator;

    @BeforeEach
    public void setup() {
        EventBuilderFactory eventBuilderFactory = mock(EventBuilderFactory.class);
        checksumValidator = mock(ChecksumValidator.class);
        instance = new EventProcessorImpl(eventBuilderFactory, checksumValidator);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, subtraindo 2, diferente de 11(hex) deve retornar false")
    public void eventProcessingTestScenario1() {
        String packet = EventProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com byte identificador diferente de B0 deve retornar false")
    public void eventProcessingTestScenario2() {
        String packet = EventProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com checksum inválido deve retornar false")
    public void eventProcessingTestScenario3() {
        String packet = EventProcessorConstants.INVALID_CHECKSUM_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(false);

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, identificador e checksum válidos deve retornar true")
    public void eventProcessingTestScenario4() {
        String packet = EventProcessorConstants.VALID_EVENT_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(true);

        boolean result = instance.canProcess(packet);

        Assertions.assertTrue(result);
    }
}