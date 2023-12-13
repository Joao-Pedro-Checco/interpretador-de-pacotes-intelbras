package br.com.fulltime.fullarm.factory;

import br.com.fulltime.fullarm.processador.*;

import java.util.HashMap;

public class PacoteFactory {
    private final HashMap<String, ProcessadorPacote> mapa = new HashMap<>();

    public PacoteFactory() {
        this.inicializarMapa();
    }

    public ProcessadorPacote buscarProcessador(String identificador) {
        return mapa.get(identificador);
    }

    private void inicializarMapa() {
        mapa.put("F7", new ProcessadorKeepAlive());
        mapa.put("B0", new ProcessadorEvento());
        mapa.put("FE", new ProcessadorAck());
        mapa.put("94", new ProcessadorAutenticacao());
        mapa.put("E0", new ProcessadorNack());
        mapa.put("E1", new ProcessadorNack());
        mapa.put("E2", new ProcessadorNack());
        mapa.put("E3", new ProcessadorNack());
        mapa.put("E4", new ProcessadorNack());
        mapa.put("E5", new ProcessadorNack());
        mapa.put("E6", new ProcessadorNack());
        mapa.put("E7", new ProcessadorNack());
        mapa.put("E8", new ProcessadorNack());
    }
}
