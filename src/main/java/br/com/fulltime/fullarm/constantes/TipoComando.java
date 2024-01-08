package br.com.fulltime.fullarm.constantes;

public enum TipoComando {
    ATIVACAO_DA_CENTRAL("41", "Ativação da Central"),
    DESATIVACAO_DA_CENTRAL("44", "Desativação da Central"),
    CONTROLE_DE_PGM("50", "Controle de PGM"),
    SOLICITACAO_PARCIAL_STATUS("5A", "Solicitação de Status Parcial"),
    SOLICITACAO_COMPLETA_STATUS("5B", "Solicitação de Status Completo"),
    BYPASS("42", "Bypass"),
    DESLIGA_SIRENE("63", "Desligar Sirene"),
    LIGA_SIRENE("43", "Ligar Sirene"),
    PANICO("45", "Pânico"),
    UNKNOWN("", "Desconhecido");

    private final String tipo;
    private final String descricao;

    TipoComando(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return descricao;
    }
}
