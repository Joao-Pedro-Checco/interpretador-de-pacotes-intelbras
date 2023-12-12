package br.com.fulltime.fullarm.infra.processor.event;

public class EventProcessorConstants {
    public static final String INVALID_SIZE_PACKAGE = "11 B0 11";
    public static final String INVALID_IDENTIFIER_PACKAGE = "11 B1 11 0A 0A 0A 01 01 08 01 04 0A 01 0A 01 0A 0A 01 49";
    public static final String INVALID_CHECKSUM_PACKAGE = "11 B0 11 0A 0A 0A 01 01 08 01 04 0A 01 0A 01 0A 0A 01 00";
    public static final String VALID_EVENT_PACKAGE = "11 B0 11 0A 0A 0A 01 01 08 01 04 0A 01 0A 01 0A 0A 01 49";
}
