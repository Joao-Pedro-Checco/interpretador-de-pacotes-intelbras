package br.com.fulltime.fullarm.infra.processor.status.full;

import br.com.fulltime.fullarm.core.message.builder.factory.FullStatusBuilderFactory;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class FullStatusProcessorTest {
    private FullStatusProcessor instance;
    private ChecksumValidator checksumValidator;

    @BeforeEach
    public void setup() {
        FullStatusInfoParser fullStatusInfoParser = mock(FullStatusInfoParser.class);
        FullStatusBuilderFactory fullStatusBuilderFactory = mock(FullStatusBuilderFactory.class);
        checksumValidator = mock(ChecksumValidator.class);
        instance = new FullStatusProcessorImpl(fullStatusInfoParser, fullStatusBuilderFactory, checksumValidator);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, subtraindo 2, diferente do valor decimal do byte identificador deve retornar false")
    public void fullStatusProcessingTestScenario1() {
        String packet = FullStatusProcessorConstants.INVALID_SIZE_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com byte identificador diferente de E9 deve retornar false")
    public void fullStatusProcessingTestScenario2() {
        String packet = FullStatusProcessorConstants.INVALID_IDENTIFIER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, subtraindo 2, diferente de 55 deve retornar false")
    public void fullStatusProcessingTestScenario3() {
        String packet = FullStatusProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com checksum inválido deve retornar false")
    public void fullStatusProcessingTestScenario4() {
        String packet = FullStatusProcessorConstants.INVALID_CHECKSUM_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(false);

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, identificador e checksum corretos deve retornar true")
    public void fullStatusProcessingTestScenario5() {
        String packet = FullStatusProcessorConstants.VALID_FULL_STATUS_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(true);

        boolean result = instance.canProcess(packet);

        Assertions.assertTrue(result);
    }
}
