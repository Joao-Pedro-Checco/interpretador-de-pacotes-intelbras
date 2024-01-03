package br.com.fulltime.fullarm.processador.status;

public enum ModeloCentral {
    AMT2008RF("08", "AMT2008RF"),
    AMT2010("10", "AMT2010"),
    AMT2018("18", "AMT2018"),
    AMT2018_E_EG("1E", "AMT2018E/EG"),
    AMT3010("30", "AMT3010"),
    AMT4010SMART("41", "AMT4010SMART"),
    GPRS1000UN("51", "GPRS1000UN"),
    AMT1016NET("61", "AMT1016NET"),
    AMT2110("20", "AMT2110"),
    AMT2118EG("2E", "AMT2118EG"),
    UNKNOWN("", "Desconhecido");

    private final String byteModelo;
    private final String descricao;

    ModeloCentral(String byteModelo, String descricao) {
        this.byteModelo = byteModelo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public String getByteModelo() {
        return byteModelo;
    }

    public static ModeloCentral getByValue(String byteModelo) {
        for (ModeloCentral modelo : values()) {
            if (byteModelo.equals(modelo.getByteModelo())) return modelo;
        }

        return ModeloCentral.UNKNOWN;
    }
}
