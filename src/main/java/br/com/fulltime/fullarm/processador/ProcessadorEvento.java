package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Pacote;

public class ProcessadorEvento implements ProcessadorPacoteFrameLongo {
    @Override
    public Pacote processar(String hexString) {
        return null;
    }

    @Override
    public String[] particionarBytes(String hexString) {
        return new String[0];
    }

    // Qualificador pode ser 1 ou 3
    // 1 - Evento | 3 - Restauração
}
