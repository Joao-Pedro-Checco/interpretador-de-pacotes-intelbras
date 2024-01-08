package br.com.fulltime.fullarm.processador.comando;

import br.com.fulltime.fullarm.constantes.TipoComando;
import br.com.fulltime.fullarm.constantes.TipoPanico;
import br.com.fulltime.fullarm.modelo.pacote.comando.subcomando.SubComando;
import br.com.fulltime.fullarm.utils.GerenciadorDeZonas;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParserSubComando {
    public TipoComando identificarTipoComando(String comando) {
        String identificador = comando.substring(0, 2);
        return TipoComando.getByValue(identificador);
    }
}
