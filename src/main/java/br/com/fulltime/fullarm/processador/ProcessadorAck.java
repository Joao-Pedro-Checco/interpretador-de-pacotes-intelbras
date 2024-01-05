package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Ack;
import br.com.fulltime.fullarm.pacote.PacoteGenerico;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorAck implements ProcessadorPacoteFrameCurto {
    @Override
    public PacoteGenerico processar(String hexString) {
        System.out.println("Montando pacote ACK...");
        System.out.println("===================================================================================");

        return new Ack();
    }
}
