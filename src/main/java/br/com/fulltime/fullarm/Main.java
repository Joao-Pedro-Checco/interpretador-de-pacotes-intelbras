package br.com.fulltime.fullarm;

import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.PacoteParser;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Main {
    private final PacoteParser pacoteParser;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Main main = applicationContext.getBean(Main.class);
        main.start();
    }

    public Main(PacoteParser pacoteParser) {
        this.pacoteParser = pacoteParser;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String hexString = FormatadorHexStr.formatar(scanner.nextLine());
//        ValidadorHexString.validar(hexString);
        ProcessadorPacote processador = pacoteParser.identificarPacote(hexString);

        Pacote pacote = processador.processar(hexString);
        System.out.println(pacote);
    }
}
