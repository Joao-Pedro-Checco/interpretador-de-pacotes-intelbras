package br.com.fulltime.fullarm;

import br.com.fulltime.fullarm.modelo.PacoteGenerico;
import br.com.fulltime.fullarm.constantes.TipoPacote;
import br.com.fulltime.fullarm.processador.factory.PacoteFactory;
import br.com.fulltime.fullarm.processador.parser.PacoteParser;
import br.com.fulltime.fullarm.processador.pacote.ProcessadorPacote;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Main {
    private final PacoteParser pacoteParser;
    private final PacoteFactory pacoteFactory;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Main main = applicationContext.getBean(Main.class);
        main.start();
    }

    public Main(PacoteParser pacoteParser, PacoteFactory pacoteFactory) {
        this.pacoteParser = pacoteParser;
        this.pacoteFactory = pacoteFactory;
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("hex string: ");
            String input = scanner.nextLine();
            if (input.equals("/exit")) break;
            String hexString = FormatadorHexStr.formatar(input);
//        ValidadorHexString.validar(hexString);
            TipoPacote identificadorPacote = pacoteParser.identificarPacote(hexString);
            ProcessadorPacote processadorPacote = pacoteFactory.buscarProcessador(identificadorPacote);
            System.out.println("Processando pacote de " + identificadorPacote);

            PacoteGenerico pacoteGenerico = processadorPacote.processar(hexString);
            System.out.println(pacoteGenerico);
        }
    }
}
