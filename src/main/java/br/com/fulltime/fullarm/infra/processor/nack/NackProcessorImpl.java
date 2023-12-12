package br.com.fulltime.fullarm.infra.processor.nack;

import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.nack.NackMessage;
import br.com.fulltime.fullarm.infra.constants.PackageIdentifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.fulltime.fullarm.infra.constants.PackageIdentifier.*;

@Service
public class NackProcessorImpl implements NackProcessor {
    private static final List<PackageIdentifier> validNacks = new ArrayList<PackageIdentifier>(){{
            add(INVALID_PACKAGE_FORMAT_NACK);
            add(INVALID_COMMAND_NACK);
            add(NON_PARTITIONED_CENTRAL_NACK);
            add(OPEN_ZONES_NACK);
            add(DISCONTINUED_COMMAND_NACK);
            add(USER_WITHOUT_PERMISSION_TO_BYPASS_NACK);
            add(USER_WITHOUT_PERMISSION_TO_DEACTIVATE_NACK);
            add(BYPASS_NOT_ALLOWED_WITH_OPEN_CENTRAL_NACK);
    }};

    @Override
    public GenericPackageMessage process(PackageUserInput packageUserInput) {
        String userInputRawContent = packageUserInput.getRawContent();
        System.out.println("Montando Pacote Nack...");
        System.out.println("===================================================================================");

        String descricao = getDescription(userInputRawContent);
        System.out.println("Adicionando Descrição: " + descricao);
        System.out.println("===================================================================================");

        NackMessage nackMessage = new NackMessage();
        nackMessage.setDescription(descricao);
        return nackMessage;
    }

    @Override
    public boolean canProcess(String userInputRawString) {
        boolean packageSizeIsValid = userInputRawString.length() == 2;
        if (!packageSizeIsValid) {
            return false;
        }

        boolean packageIdentifierIsValid = validNacks.contains(PackageIdentifier.getByValue(userInputRawString));
        if (!packageIdentifierIsValid) {
            return false;
        }

        return true;
    }

    private String getDescription(String identifier) {
        switch (identifier) {
            case "E0": {
                return "Formato de pacote inválido";
            }
            case "E1": {
                return "Senha incorreta";
            }
            case "E2": {
                return "Comando inválido";
            }
            case "E3": {
                return "Central não particionada";
            }
            case "E4": {
                return "Zonas abertas";
            }
            case "E5": {
                return "Comando descontinuado";
            }
            case "E6": {
                return "Usuário sem permissão para Bypass";
            }
            case "E7": {
                return "Usuário sem permissão para desativar";
            }
            case "E8": {
                return "Bypass não permitido com a central ativa";
            }
            default: {
                return "Desconhecido";
            }
        }
    }
}
