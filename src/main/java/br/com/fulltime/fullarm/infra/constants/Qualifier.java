package br.com.fulltime.fullarm.infra.constants;

public enum Qualifier {
    EVENT("01", "Evento"),
    RESTORATION("03", "Restauração"),
    UNKNOWN("", "Desconhecido");

    private final String identifier;
    private final String description;

    Qualifier(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Qualifier getByValue(String identifier) {
        for (Qualifier q : values()) {
            if (identifier.equals(q.getIdentifier())) return q;
        }

        return Qualifier.UNKNOWN;
    }

    @Override
    public String toString() {
        return description;
    }
}
