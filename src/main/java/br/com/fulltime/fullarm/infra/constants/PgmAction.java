package br.com.fulltime.fullarm.infra.constants;

public enum PgmAction {
    TURN_PGM_ON("4C", "Ligar PGM"),
    TURN_PGM_OFF("44", "Desligar PGM"),
    UNKNOWN("", "Desconhecido");

    private final String identifier;
    private final String description;

    PgmAction(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static PgmAction getByValue(String identifier) {
        for (PgmAction action : values()) {
            if (identifier.equals(action.getIdentifier())) return action;
        }

        return PgmAction.UNKNOWN;
    }

    @Override
    public String toString() {
        return description;
    }
}
