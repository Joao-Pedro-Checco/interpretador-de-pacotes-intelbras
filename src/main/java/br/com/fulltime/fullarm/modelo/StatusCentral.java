package br.com.fulltime.fullarm.modelo;

public enum StatusCentral {
    CENTRAL_ATIVADA("08", "Central Ativada"),
    ZONA_DISPARADA_1("44", "Alguma zona está disparada"),
    ZONA_DISPARADA_2("04", "Alguma zona está disparada"),
    SIRENE_LIGADA("02", "Sirene ligada"),
    PROBLEMA_NA_CENTRAL("11", "Problema na Central"),
    UNKNOWN("", "UNKNOWN");

    private final String codigoStatus;
    private final String descricao;

    StatusCentral(String codigoStatus, String descricao) {
        this.codigoStatus = codigoStatus;
        this.descricao = descricao;
    }

    public String getCodigoStatus() {
        return codigoStatus;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static StatusCentral getByValue(String codigoStatus) {
        for (StatusCentral sc : values()) {
            if (codigoStatus.equals(sc.getCodigoStatus())) return sc;
        }

        return StatusCentral.UNKNOWN;
    }
}
