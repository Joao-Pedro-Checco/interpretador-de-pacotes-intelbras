package br.com.fulltime.fullarm;

import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.PacoteParser;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;
import br.com.fulltime.fullarm.processador.ProcessadorPacoteFrameLongo;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hexString = FormatadorHexStr.formatar(scanner.nextLine());
        ProcessadorPacote processador = new PacoteParser(hexString).identificarPacote();

        Pacote pacote = processador.processar(hexString);
        System.out.println(pacote);
    }
}
