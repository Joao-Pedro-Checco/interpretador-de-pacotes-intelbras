package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

import java.util.List;

public class CentralDeactivation extends Subcommand {
    private List<Character> partitions;

    public CentralDeactivation() {
        super(SubcommandType.CENTRAL_DEACTIVATION);
    }

    public List<Character> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Character> partitions) {
        this.partitions = partitions;
    }

    @Override
    public String toString() {
        return "CentralDeactivation{" +
                "partitions=" + partitions +
                '}';
    }
}
