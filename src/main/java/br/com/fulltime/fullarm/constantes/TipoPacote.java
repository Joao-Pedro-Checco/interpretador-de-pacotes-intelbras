package br.com.fulltime.fullarm.constantes;

public enum TipoPacote {
    ACK("FE"),
    AUTENTICACAO("94"),
    EVENTO("B0"),
    KEEP_ALIVE("F7"),
    NACK_FORMATO_PACOTE_INVALIDO("E0"),
    NACK_SENHA_INCORRETA("E1"),
    NACK_COMANDO_INVALIDO("E2"),
    NACK_CENTRAL_NAO_PARTICIONADA("E3"),
    NACK_ZONAS_ABERTAS("E4"),
    NACK_COMANDO_DESCONTINUADO("E5"),
    NACK_USUARIO_SEM_PERMISSAO_PARA_BYPASS("E6"),
    NACK_USUARIO_SEM_PERMISSAO_PARA_DESATIVAR("E7"),
    NACK_BYPASS_NAO_PERMITIDO_COM_CENTRAL_ATIVADA("E8"),
    COMANDO("E9"),
    STATUS_PARCIAL("STP"),
    STATUS_COMPLETO("STC"),
    UNKNOWN("");

    private final String identificador;

    public String getIdentificador() {
        return identificador;
    }

    TipoPacote(String identificador) {
        this.identificador = identificador;
    }

    public static TipoPacote getByValue(String identificador) {
        for (TipoPacote tp : values()) {
            if (identificador.equals(tp.getIdentificador())) return tp;
        }

        return TipoPacote.UNKNOWN;
    }
}
