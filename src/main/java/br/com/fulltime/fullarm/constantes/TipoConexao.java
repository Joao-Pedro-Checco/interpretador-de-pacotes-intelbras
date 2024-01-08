package br.com.fulltime.fullarm.constantes;

public enum TipoConexao {
    ETHERNET("45", "Ethernet"),
    GPRS_1("47", "GPRS 1"),
    GPRS_2("48", "GPRS 2"),
    ETHERNET_IP_1("11", "Ethernet IP 1"),
    ETHERNET_IP_2("12", "Ethernet IP 2"),
    GPRS_IP_1("21", "GPRS IP 1"),
    GPRS_IP_2("22", "GPRS IP 2"),
    UNKNOWN("", "Desconhecido");

    private final String byteConexao;
    private final String descricao;

    TipoConexao(String byteConexao, String descricao) {
        this.byteConexao = byteConexao;
        this.descricao = descricao;
    }

    public String getByteConexao() {
        return byteConexao;
    }

    public static TipoConexao getByValue(String byteConexao) {
        for (TipoConexao tc : values()) {
            if (byteConexao.equals(tc.getByteConexao())) return tc;
        }

        return TipoConexao.UNKNOWN;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
