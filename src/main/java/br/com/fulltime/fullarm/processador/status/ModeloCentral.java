package br.com.fulltime.fullarm.processador.status;

public enum ModeloCentral {
    AMT2008RF("08", "AMT 2008 RF"),
    AMT2010("10", "AMT 2010"),
    AMT2018("18", "AMT 2018"),
    AMT2018EEG("1E", "AMT 2018 E/EG"),
    AMT3010("30", "AMT 3010"),
    AMT4010SMART("41", "AMT 4010 SMART"),
    GPRS1000UN("51", "GPRS 1000 UN"),
    AMT1016NET("61", "AMT 1016 NET"),
    AMT2110("20", "AMT 2110"),
    AMT2118EG("2E", "AMT 2118 EG"),
    ANM24NET("24", "ANM 24 NET"),
    AMT2018E3G("32", "AMT 2018 E3G"),
    AMT2018ESMART("34", "AMT 2018 E SMART"),
    ELC3020NET("35", "ELC 3020 NET"),
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
