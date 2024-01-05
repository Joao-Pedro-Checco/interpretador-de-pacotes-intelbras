package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.KeepAlive;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorKeepAlive implements ProcessadorPacoteFrameCurto {
    @Override
    public PacoteGenerico processar(String hexString) {
        System.out.println("Processando mensagem Keep Alive...");
        System.out.println("===================================================================================");

        return new KeepAlive();
    }
}
