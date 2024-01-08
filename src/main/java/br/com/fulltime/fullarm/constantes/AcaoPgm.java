package br.com.fulltime.fullarm.constantes;

public enum AcaoPgm {
    LIGAR_PGM("4C", "Ligar PGM"),
    DESLIGAR_PGM("44", "Desligar PGM"),
    UNKNOWN("", "Desconhecido");

    private String identificador;
    private String descricao;

    AcaoPgm(String identificador, String descricao) {
        this.identificador = identificador;
        this.descricao = descricao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public static AcaoPgm getByValue(String identificador) {
        for (AcaoPgm acao : values()) {
            if (identificador.equals(acao.getIdentificador())) return acao;
        }

        return AcaoPgm.UNKNOWN;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
