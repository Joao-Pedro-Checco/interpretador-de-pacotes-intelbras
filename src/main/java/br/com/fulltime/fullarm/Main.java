package br.com.fulltime.fullarm;

import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.PacoteParser;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hexString = FormatadorHexStr.formatar(scanner.nextLine());
        Pacote pacote = new PacoteParser(hexString).identificarPacote();
        System.out.println(pacote);
    }
}
