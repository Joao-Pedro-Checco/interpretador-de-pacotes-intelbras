package br.com.fulltime.fullarm.core.message.handler;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;

import java.util.List;

public interface PackageMessageHandler {
    List<String> handleMessage(GenericPackageMessage genericPackageMessage);
}
