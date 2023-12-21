package br.com.fulltime.fullarm.pacote;

public enum TipoComando {
    ATIVACAO_DA_CENTRAL("41"),
    DESATIVACAO_DA_CENTRAL("44"),
    CONTROLE_DE_PGM("50"),
    SOLICITACAO_PARCIAL_STATUS("5A"),
    SOLICITACAO_COMPLETA_STATUS("5B"),
    BYPASS("42"),
    DESLIGA_SIRENE("63"),
    LIGA_SIRENE("43"),
    PANICO("45"),
    UNKNOWN("");

    private final String tipo;

    TipoComando(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoComando getByValue(String tipo) {
        for (TipoComando tipoComando : values()) {
            if (tipo.equals(tipoComando.getTipo())) return tipoComando;
        }

        return TipoComando.UNKNOWN;
    }
}
