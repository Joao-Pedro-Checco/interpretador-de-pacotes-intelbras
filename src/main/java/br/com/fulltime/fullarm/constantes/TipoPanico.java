package br.com.fulltime.fullarm.constantes;

public enum TipoPanico {
    SILENCIOSO("00", "Pânico Silencioso"),
    AUDIVEL("01", "Pânico Audível"),
    EMERGENCIA_MEDICA("02", "Emergência Médica"),
    INCENDIO("03", "Incêndio"),
    UNKNOWN("", "Desconhecido");

    private final String identificador;
    private final String descricao;

    TipoPanico(String identificador, String descricao) {
        this.identificador = identificador;
        this.descricao = descricao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public static TipoPanico getByValue(String identificador) {
        for (TipoPanico tp : values()) {
            if (identificador.equals(tp.getIdentificador())) return tp;
        }

        return TipoPanico.UNKNOWN;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
