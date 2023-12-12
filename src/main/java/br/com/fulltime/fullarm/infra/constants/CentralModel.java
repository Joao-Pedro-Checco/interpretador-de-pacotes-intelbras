package br.com.fulltime.fullarm.infra.constants;

public enum CentralModel {
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

    private final String modelByte;
    private final String description;

    CentralModel(String modelByte, String description) {
        this.modelByte = modelByte;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getModelByte() {
        return modelByte;
    }

    public static CentralModel getByValue(String modelByte) {
        for (CentralModel model : values()) {
            if (modelByte.equals(model.getModelByte())) return model;
        }

        return CentralModel.UNKNOWN;
    }
}
