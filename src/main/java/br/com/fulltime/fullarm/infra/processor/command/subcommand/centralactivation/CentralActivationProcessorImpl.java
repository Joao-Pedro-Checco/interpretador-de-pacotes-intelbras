package br.com.fulltime.fullarm.infra.processor.command.subcommand.centralactivation;

import br.com.fulltime.fullarm.core.message.command.subcommand.CentralActivation;
import br.com.fulltime.fullarm.core.message.command.subcommand.Subcommand;
import br.com.fulltime.fullarm.infra.constants.SubcommandIdentifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentralActivationProcessorImpl implements CentralActivationProcessor {
    public final static String alphabetMap = "ABCD";
    private final static List<String> validPartitions = Arrays.asList("41", "42", "43", "44");

    @Override
    public Subcommand process(String argument) {
        List<String> bytes = splitBytes(argument);
        System.out.println("Processando subcomando de Ativação da Central...");
        List<Character> partitions = parsePartitions(bytes);
        System.out.println("Adicionando partições: " + partitions);
        CentralActivation centralActivation = new CentralActivation();
        centralActivation.setPartitions(partitions);
        return centralActivation;
    }

    @Override
    public boolean canProcess(String arguments) {
        List<String> bytes = splitBytes(arguments);
        boolean subcommandIdentifierIsValid = SubcommandIdentifier.getByValue(bytes.get(0)) == SubcommandIdentifier.CENTRAL_ACTIVATION;
        if (!subcommandIdentifierIsValid) {
            return false;
        }

        List<String> partitions = bytes.subList(1, bytes.size());
        boolean partitionsAreValid = partitions.isEmpty() || new HashSet<>(validPartitions).containsAll(partitions);
        if (!partitionsAreValid) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> splitBytes(String arguments) {
        return Arrays.asList(arguments.split(" "));
    }

    private List<Character> parsePartitions(List<String> bytes) {
        if (bytes.size() == 1) return Arrays.asList('A', 'B', 'C', 'D');

        List<String> partitionBytes = bytes.subList(1, bytes.size());
        return partitionBytes.stream()
                .map(b -> Integer.parseInt(b, 16) - 0x41)
                .map(alphabetMap::charAt)
                .collect(Collectors.toList());
    }
}
