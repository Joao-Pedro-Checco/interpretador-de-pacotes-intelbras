package br.com.fulltime.fullarm.infra.constants;

public enum PartitionStatus {
    ENABLED("01", "Partição Habilitada"),
    DISABLED("00", "Partição Desabilitada"),
    UNKNOWN("", "UNKNOWN");

    private final String statusCode;
    private final String description;

    PartitionStatus(String statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public static PartitionStatus getByValue(String statusCode) {
        for (PartitionStatus ps : values()) {
            if (statusCode.equals(ps.getStatusCode())) return ps;
        }

        return PartitionStatus.UNKNOWN;
    }
}
