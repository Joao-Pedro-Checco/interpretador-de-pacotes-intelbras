package br.com.fulltime.fullarm.processador.pacote.comando;

import br.com.fulltime.fullarm.constantes.TipoComando;
import org.springframework.stereotype.Service;

@Service
public class ParserSubComando {
    public TipoComando identificarTipoComando(String comando) {
        String identificador = comando.substring(0, 2);
        return TipoComando.getByValue(identificador);
    }
}
