package br.com.fulltime.fullarm.infra.processor.status.partial;

import br.com.fulltime.fullarm.core.message.builder.factory.PartialStatusBuilderFactory;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class PartialStatusProcessorTest {
    private PartialStatusProcessor instance;
    private ChecksumValidator checksumValidator;

    @BeforeEach
    public void setup() {
        PartialStatusInfoParser fullStatusInfoParser = mock(PartialStatusInfoParser.class);
        PartialStatusBuilderFactory fullStatusBuilderFactory = mock(PartialStatusBuilderFactory.class);
        checksumValidator = mock(ChecksumValidator.class);
        instance = new PartialStatusProcessorImpl(fullStatusInfoParser, fullStatusBuilderFactory, checksumValidator);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, subtraindo 2, diferente do valor decimal do byte identificador deve retornar false")
    public void partialStatusProcessingTestScenario1() {
        String packet = PartialStatusProcessorConstants.INVALID_SIZE_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com byte identificador diferente de E9 deve retornar false")
    public void partialStatusProcessingTestScenario2() {
        String packet = PartialStatusProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, subtraindo 2, diferente de 44 deve retornar false")
    public void partialStatusProcessingTestScenario3() {
        String packet = PartialStatusProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com checksum inválido deve retornar false")
    public void partialStatusProcessingTestScenario4() {
        String packet = PartialStatusProcessorConstants.INVALID_CHECKSUM_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(false);

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, identificador e checksum corretos deve retornar true")
    public void partialStatusProcessingTestScenario5() {
        String packet = PartialStatusProcessorConstants.VALID_PARTIAL_STATUS_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(true);

        boolean result = instance.canProcess(packet);

        Assertions.assertTrue(result);
    }
}
