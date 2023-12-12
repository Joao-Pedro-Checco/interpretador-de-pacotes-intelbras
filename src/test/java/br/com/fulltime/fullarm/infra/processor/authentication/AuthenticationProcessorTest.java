package br.com.fulltime.fullarm.infra.processor.authentication;

import br.com.fulltime.fullarm.core.message.builder.factory.AuthenticationBuilderFactory;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class AuthenticationProcessorTest {
    private AuthenticationProcessor instance;
    private ChecksumValidator checksumValidator;

    @BeforeEach
    public void setup() {
        AuthenticationBuilderFactory authenticationBuilderFactory = mock(AuthenticationBuilderFactory.class);
        checksumValidator = mock(ChecksumValidator.class);
        instance = new AuthenticationProcessorImpl(authenticationBuilderFactory, checksumValidator);
    }

    @Test
    @DisplayName("Quando o tamanho do pacote, subtraindo 2, é diferente do byte identificador de tamanho deve retornar false")
    public void authenticationProcessingTestScenario2() {
        String packet = AuthenticationProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando byte identificador do pacote não for o identificador de Autenticação deve retornar false")
    public void authenticationProcessingTestScenario3() {
        String packet = AuthenticationProcessorConstants.INVALID_PACKAGE_IDENTIFIER;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando checksum do pacote for inválido deve retornar false")
    public void authenticationProcessingTestScenario4() {
        String packet = AuthenticationProcessorConstants.INVALID_CHECKSUM_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(false);

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com identificador de tamanho, identificador e checksum corretos deve retornar true")
    public void authenticationProcessingTestScenario5() {
        String packet = AuthenticationProcessorConstants.VALID_AUTHENTICATION_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(true);

        boolean result = instance.canProcess(packet);

        Assertions.assertTrue(result);
    }
}
