package br.com.fulltime.fullarm.infra.processor.command;

import br.com.fulltime.fullarm.core.message.builder.factory.CommandBuilderFactory;
import br.com.fulltime.fullarm.infra.processor.ChecksumValidator;
import br.com.fulltime.fullarm.infra.processor.command.subcommand.SubcommandProcessorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class CommandProcessorTest {
    private CommandProcessor instance;
    private ChecksumValidator checksumValidator;

    @BeforeEach
    public void setup() {
        SubcommandProcessorFactory subcommandProcessorFactory = mock(SubcommandProcessorFactory.class);
        CommandBuilderFactory commandBuilderFactory = mock(CommandBuilderFactory.class);
        checksumValidator = mock(ChecksumValidator.class);
        instance = new CommandProcessorImpl(subcommandProcessorFactory, commandBuilderFactory, checksumValidator);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho diferente do byte de tamanho menos 2 deve retornar false")
    public void commandProcessingTestScenario1() {
        String packet = CommandProcessorConstants.INVALID_SIZE_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com identificador diferente de E9 deve retornar false")
    public void commandProcessingTestScenario2() {
        String packet = CommandProcessorConstants.INVALID_PACKAGE_IDENTIFIER;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com byte delimitador de subcomando diferente de 21 deve retornar false")
    public void commandProcessingTestScenario3() {
        String packet = CommandProcessorConstants.INVALID_DATA_DELIMITER_PACKAGE;

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com checksum inválido deve retornar false")
    public void commandProcessingTestScenario4() {
        String packet = CommandProcessorConstants.INVALID_CHECKSUM_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(false);

        boolean result = instance.canProcess(packet);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Quando receber pacote com tamanho, identificador, delimitador e checksum corretos deve retornar true")
    public void commandProcessingScenario5() {
        String packet = CommandProcessorConstants.VALID_COMMAND_PACKAGE;
        Mockito.when(checksumValidator.checksumIsValid(anyString())).thenReturn(true);

        boolean result = instance.canProcess(packet);

        Assertions.assertTrue(result);
    }
}
