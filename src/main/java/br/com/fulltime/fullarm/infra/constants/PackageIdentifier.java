package br.com.fulltime.fullarm.infra.constants;

public enum PackageIdentifier {
    ACK("FE"),
    AUTHENTICATION("94"),
    EVENT("B0"),
    KEEP_ALIVE("F7"),
    INVALID_PACKAGE_FORMAT_NACK("E0"),
    WRONG_PASSWORD_NACK("E1"),
    INVALID_COMMAND_NACK("E2"),
    NON_PARTITIONED_CENTRAL_NACK("E3"),
    OPEN_ZONES_NACK("E4"),
    DISCONTINUED_COMMAND_NACK("E5"),
    USER_WITHOUT_PERMISSION_TO_BYPASS_NACK("E6"),
    USER_WITHOUT_PERMISSION_TO_DEACTIVATE_NACK("E7"),
    BYPASS_NOT_ALLOWED_WITH_OPEN_CENTRAL_NACK("E8"),
    COMMAND("E9"),
    UNKNOWN("");

    private final String identifier;

    public String getIdentifier() {
        return identifier;
    }

    PackageIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public static PackageIdentifier getByValue(String identifier) {
        for (PackageIdentifier pt : values()) {
            if (identifier.equals(pt.getIdentifier())) return pt;
        }

        return PackageIdentifier.UNKNOWN;
    }
}
