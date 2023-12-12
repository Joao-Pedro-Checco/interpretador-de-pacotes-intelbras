package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;

import java.util.List;

public class Bypass extends Subcommand {
    private List<Integer> annulledZones;

    public Bypass() {
        super(SubcommandType.BYPASS);
    }

    public List<Integer> getAnnulledZones() {
        return annulledZones;
    }

    public void setAnnulledZones(List<Integer> annulledZones) {
        this.annulledZones = annulledZones;
    }

    @Override
    public String toString() {
        return "Bypass{" +
                "annulledZones=" + annulledZones +
                '}';
    }
}
