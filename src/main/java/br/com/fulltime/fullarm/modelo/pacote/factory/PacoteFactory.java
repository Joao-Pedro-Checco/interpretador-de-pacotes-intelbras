package br.com.fulltime.fullarm.modelo.pacote.factory;

import br.com.fulltime.fullarm.constantes.TipoPacote;
import br.com.fulltime.fullarm.processador.*;
import br.com.fulltime.fullarm.processador.comando.ProcessadorComando;
import br.com.fulltime.fullarm.processador.status.completo.ProcessadorStatusCompleto;
import br.com.fulltime.fullarm.processador.status.parcial.ProcessadorStatusParcial;
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
    private final HashMap<TipoPacote, ProcessadorPacote> mapa = new HashMap<>();

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
        mapa.put(TipoPacote.KEEP_ALIVE, processadorKeepAlive);
        mapa.put(TipoPacote.EVENTO, processadorEvento);
        mapa.put(TipoPacote.ACK, processadorAck);
        mapa.put(TipoPacote.AUTENTICACAO, processadorAutenticacao);
        mapa.put(TipoPacote.NACK_FORMATO_PACOTE_INVALIDO, processadorNack);
        mapa.put(TipoPacote.NACK_SENHA_INCORRETA, processadorNack);
        mapa.put(TipoPacote.NACK_COMANDO_INVALIDO, processadorNack);
        mapa.put(TipoPacote.NACK_CENTRAL_NAO_PARTICIONADA, processadorNack);
        mapa.put(TipoPacote.NACK_ZONAS_ABERTAS, processadorNack);
        mapa.put(TipoPacote.NACK_COMANDO_DESCONTINUADO, processadorNack);
        mapa.put(TipoPacote.NACK_USUARIO_SEM_PERMISSAO_PARA_BYPASS, processadorNack);
        mapa.put(TipoPacote.NACK_USUARIO_SEM_PERMISSAO_PARA_DESATIVAR, processadorNack);
        mapa.put(TipoPacote.NACK_BYPASS_NAO_PERMITIDO_COM_CENTRAL_ATIVADA, processadorNack);
        mapa.put(TipoPacote.COMANDO, processadorComando);
        mapa.put(TipoPacote.STATUS_PARCIAL, processadorStatusParcial);
        mapa.put(TipoPacote.STATUS_COMPLETO, processadorStatusCompleto);
    }

    public ProcessadorPacote buscarProcessador(TipoPacote identificador) {
        if (mapa.isEmpty()) inicializarMapa();
        return mapa.get(identificador);
    }
}
