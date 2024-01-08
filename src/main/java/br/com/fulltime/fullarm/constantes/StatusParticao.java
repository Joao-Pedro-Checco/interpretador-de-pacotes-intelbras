package br.com.fulltime.fullarm.constantes;

public enum StatusParticao {
    HABILITADA("01", "Partição Habilitada"),
    DESABILITADA("00", "Partição Desabilitada"),
    UNKNOWN("", "UNKNOWN");

    private final String codigoStatus;
    private final String descricao;

    StatusParticao(String codigoStatus, String descricao) {
        this.codigoStatus = codigoStatus;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public String getCodigoStatus() {
        return codigoStatus;
    }

    public static StatusParticao getByValue(String codigoStatus) {
        for (StatusParticao sp : values()) {
            if (codigoStatus.equals(sp.getCodigoStatus())) return sp;
        }

        return StatusParticao.UNKNOWN;
    }
}
