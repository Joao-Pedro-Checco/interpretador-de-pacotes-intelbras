package br.com.fulltime.fullarm.pacote.factory;

import br.com.fulltime.fullarm.processador.*;
import br.com.fulltime.fullarm.processador.comando.ProcessadorComando;
import br.com.fulltime.fullarm.processador.status.ProcessadorStatusCompleto;
import br.com.fulltime.fullarm.processador.status.ProcessadorStatusParcial;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PacoteFactory {
    private final ProcessadorAck processadorAck;
    private final ProcessadorNack processadorNack;
    private final ProcessadorAutenticacao processadorAutenticacao;
    private final ProcessadorEvento processadorEvento;
    private final ProcessadorKeepAlive processadorKeepAlive;
    private final ProcessadorComando processadorComando;
    private final ProcessadorStatusParcial processadorStatusParcial;
    private final ProcessadorStatusCompleto processadorStatusCompleto;
    private final HashMap<String, ProcessadorPacote> mapa = new HashMap<>();

    public PacoteFactory(ProcessadorAck processadorAck,
                         ProcessadorNack processadorNack,
                         ProcessadorAutenticacao processadorAutenticacao,
                         ProcessadorEvento processadorEvento,
                         ProcessadorKeepAlive processadorKeepAlive,
                         ProcessadorComando processadorComando,
                         ProcessadorStatusParcial processadorStatusParcial,
                         ProcessadorStatusCompleto processadorStatusCompleto) {
        this.processadorAck = processadorAck;
        this.processadorNack = processadorNack;
        this.processadorAutenticacao = processadorAutenticacao;
        this.processadorEvento = processadorEvento;
        this.processadorKeepAlive = processadorKeepAlive;
        this.processadorComando = processadorComando;
        this.processadorStatusParcial = processadorStatusParcial;
        this.processadorStatusCompleto = processadorStatusCompleto;
    }

    private void inicializarMapa() {
        mapa.put("F7", processadorKeepAlive);
        mapa.put("B0", processadorEvento);
        mapa.put("FE", processadorAck);
        mapa.put("94", processadorAutenticacao);
        mapa.put("E0", processadorNack);
        mapa.put("E1", processadorNack);
        mapa.put("E2", processadorNack);
        mapa.put("E3", processadorNack);
        mapa.put("E4", processadorNack);
        mapa.put("E5", processadorNack);
        mapa.put("E6", processadorNack);
        mapa.put("E7", processadorNack);
        mapa.put("E8", processadorNack);
        mapa.put("E9", processadorComando);
        mapa.put("STP", processadorStatusParcial);
        mapa.put("STC", processadorStatusCompleto);
    }

    public ProcessadorPacote buscarProcessador(String identificador) {
        if (mapa.isEmpty()) inicializarMapa();
        return mapa.get(identificador);
    }
}
