package br.com.fulltime.fullarm.infra.processor.command;

public class CommandProcessorConstants {
    public static final String INVALID_SIZE_PACKAGE = "0B E9";
    public static final String INVALID_PACKAGE_IDENTIFIER = "0B EE 21 38 37 38 37 38 37 41 42 21 00";
    public static final String INVALID_DATA_DELIMITER_PACKAGE = "0B E9 11 38 37 38 37 38 37 41 42 21 00";
    public static final String INVALID_CHECKSUM_PACKAGE = "0B E9 21 38 37 38 37 38 37 41 42 21 00";
    public static final String VALID_COMMAND_PACKAGE = "0B E9 21 38 37 38 37 38 37 41 42 21 11";
}
