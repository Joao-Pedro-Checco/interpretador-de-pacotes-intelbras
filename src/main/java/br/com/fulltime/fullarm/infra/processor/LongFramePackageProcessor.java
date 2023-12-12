package br.com.fulltime.fullarm.infra.processor;

import java.util.List;

public interface LongFramePackageProcessor extends PackageProcessor {
    List<String> splitBytes(String hexString);
}
