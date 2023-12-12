package br.com.fulltime.fullarm.infra.constants;

public enum SubcommandIdentifier {
    CENTRAL_ACTIVATION("41"),
    CENTRAL_DEACTIVATION("44"),
    PGM_CONTROL("50"),
    PARTIAL_STATUS_REQUEST("5A"),
    FULL_STATUS_REQUEST("5B"),
    BYPASS("42"),
    TURN_SIREN_OFF("63"),
    TURN_SIREN_ON("43"),
    PANIC("45"),
    UNKNOWN("");

    private final String type;

    SubcommandIdentifier(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SubcommandIdentifier getByValue(String type) {
        for (SubcommandIdentifier ct : values()) {
            if (type.equals(ct.getType())) return ct;
        }

        return SubcommandIdentifier.UNKNOWN;
    }
}
