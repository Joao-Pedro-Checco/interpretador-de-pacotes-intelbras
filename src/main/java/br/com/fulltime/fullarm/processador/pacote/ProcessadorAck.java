package br.com.fulltime.fullarm.processador.pacote;

import br.com.fulltime.fullarm.modelo.Ack;
import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorAck implements ProcessadorPacoteFrameCurto {
    @Override
    public PacoteGenerico processar(String hexString) {
        System.out.println("Processando pacote ACK...");
        System.out.println("===================================================================================");

        return new Ack();
    }
}
