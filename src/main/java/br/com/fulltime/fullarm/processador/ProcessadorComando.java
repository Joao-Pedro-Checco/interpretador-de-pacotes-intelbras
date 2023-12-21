package br.com.fulltime.fullarm.processador;

import br.com.fulltime.fullarm.pacote.Comando;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.TipoPacote;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorComando implements ProcessadorPacoteFrameLongo {
    private List<String> bytes = new ArrayList<>();
    private final MontadorComando montadorComando;

    public ProcessadorComando(MontadorComando montadorComando) {
        this.montadorComando = montadorComando;
    }

    @Override
    public Pacote processar(String hexString) {
        particionarBytes(hexString);
        return montarPacote();
    }

    @Override
    public void particionarBytes(String hexString) {
        this.bytes = Arrays.asList(hexString.split(" "));
    }

    private Pacote montarPacote() {
        return new Comando(
                TipoPacote.COMANDO,
                getSenha(),
                getComando(),
                getDescricaoComando(),
                getChecksum()
        );
    }

    private String getSenha() {
        String hexStringSenha = String.join("", this.bytes.subList(3, 9));
        return FormatadorHexStr.hexParaAscii(hexStringSenha);
    }

    private String getComando() {
        int inicioComando = 9;
        List<String> bytesSenha = new ArrayList<>();
        for (int i = inicioComando; !bytes.get(i).equals("21"); i++) {
            bytesSenha.add(bytes.get(i));
        }

        return String.join(" ", bytesSenha);
    }

    private String getDescricaoComando() {
        return montadorComando.montar(getComando());
    }

    private String getChecksum() {
        return this.bytes.get(this.bytes.size() - 1);
    }
}
