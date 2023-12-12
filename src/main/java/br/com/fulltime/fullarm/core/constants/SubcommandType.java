package br.com.fulltime.fullarm.core.constants;

public enum SubcommandType {
    CENTRAL_ACTIVATION("Ativação da central"),
    CENTRAL_DEACTIVATION("Desativação da central"),
    PGM_CONTROL("Controle de Pgm"),
    PARTIAL_STATUS_REQUEST("Solicitação Parcial de Status"),
    FULL_STATUS_REQUEST("Solicitação Completa de Status"),
    BYPASS("Bypass"),
    TURN_SIREN_ON("Ligar Sirene"),
    TURN_SIREN_OFF("Desligar Sirene"),
    PANIC("Pânico"),
    UNKNOWN("Desconhecido");

    private final String description;

    SubcommandType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
