package br.com.fulltime.fullarm.core.message.command.subcommand;

import br.com.fulltime.fullarm.core.constants.SubcommandType;
import br.com.fulltime.fullarm.infra.constants.PgmAction;

import java.util.List;

public class PgmControl extends Subcommand {
    private PgmAction pgmAction;
    private List<Integer> pgms;

    public PgmControl() {
        super(SubcommandType.PGM_CONTROL);
    }

    public PgmAction getPgmAction() {
        return pgmAction;
    }

    public List<Integer> getPgms() {
        return pgms;
    }

    public void setPgmAction(PgmAction pgmAction) {
        this.pgmAction = pgmAction;
    }

    public void setPgms(List<Integer> pgms) {
        this.pgms = pgms;
    }

    @Override
    public String toString() {
        return "PgmControl{" +
                "pgmAction=" + pgmAction +
                ", pgms=" + pgms +
                '}';
    }
}
