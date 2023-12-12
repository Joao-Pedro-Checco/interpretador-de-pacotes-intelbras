package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

import java.util.List;

public class CentralActivation extends Subcommand {
    private List<Character> partitions;
    public CentralActivation() {
        super(SubcommandType.CENTRAL_ACTIVATION);
    }

    public List<Character> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Character> partitions) {
        this.partitions = partitions;
    }

    @Override
    public String toString() {
        return "CentralActivation{" +
                "partitions=" + partitions +
                '}';
    }
}
