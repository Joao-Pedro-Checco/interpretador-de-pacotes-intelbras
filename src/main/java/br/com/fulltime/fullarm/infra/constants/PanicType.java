package br.com.fulltime.fullarm.infra.constants;

public enum PanicType {
    SILENT("00", "Pânico Silencioso"),
    AUDIBLE("01", "Pânico Audível"),
    MEDICAL_EMERGENCY("02", "Emergência Médica"),
    FIRE("03", "Incêndio"),
    UNKNOWN("", "Desconhecido");

    private final String identifier;
    private final String description;

    PanicType(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static PanicType getByValue(String identifier) {
        for (PanicType pt : values()) {
            if (identifier.equals(pt.getIdentifier())) return pt;
        }

        return PanicType.UNKNOWN;
    }

    @Override
    public String toString() {
        return description;
    }
}
