package br.com.fulltime.fullarm.processador.pacote;

import br.com.fulltime.fullarm.modelo.Nack;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProcessadorNack implements ProcessadorPacoteFrameCurto {
    private final HashMap<String, String> mapaDescricao = new HashMap<>();

    @Override
    public PacoteGenerico processar(String hexString) {
        System.out.println("Montando Pacote Nack...");
        System.out.println("===================================================================================");

        String descricao = getDescricao(hexString);
        System.out.println("Adicionando Descrição: " + descricao);
        System.out.println("===================================================================================");

        return new Nack(descricao);
    }

    private String getDescricao(String key) {
        if (mapaDescricao.isEmpty()) inicializarMapaDescricao();
        return mapaDescricao.get(key);
    }

    private void inicializarMapaDescricao() {
        mapaDescricao.put("E0", "Formato de pacote inválido");
        mapaDescricao.put("E1", "Senha incorreta");
        mapaDescricao.put("E2", "Comando inválido");
        mapaDescricao.put("E3", "Central não particionada");
        mapaDescricao.put("E4", "Zonas abertas");
        mapaDescricao.put("E5", "Comando descontinuado");
        mapaDescricao.put("E6", "Usuário sem permissão para Bypass");
        mapaDescricao.put("E7", "Usuário sem permissão para desativar");
        mapaDescricao.put("E8", "Bypass não permitido com a central ativa");
    }
}
