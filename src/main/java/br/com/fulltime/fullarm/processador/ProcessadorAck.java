package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Ack;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.TipoPacote;

public class ProcessadorAck implements ProcessadorPacoteFrameCurto {
    @Override
    public Pacote processar(String hexString) {
        return new Ack(TipoPacote.ACK);
    }
}
