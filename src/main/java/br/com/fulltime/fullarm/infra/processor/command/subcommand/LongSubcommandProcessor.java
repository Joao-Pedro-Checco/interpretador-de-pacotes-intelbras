package br.com.fulltime.fullarm.infra.processor.command.subcommand;

import java.util.List;

public interface LongSubcommandProcessor extends SubcommandProcessor {
    List<String> splitBytes(String arguments);
}
