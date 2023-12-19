package br.com.fulltime.fullarm;

import br.com.fulltime.fullarm.factory.PacoteFactory;
import br.com.fulltime.fullarm.pacote.Pacote;
import br.com.fulltime.fullarm.pacote.PacoteParser;
import br.com.fulltime.fullarm.processador.ProcessadorPacote;
import br.com.fulltime.fullarm.utils.FormatadorHexStr;
import br.com.fulltime.fullarm.utils.ValidadorHexString;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hexString = FormatadorHexStr.formatar(scanner.nextLine());
        ValidadorHexString.validar(hexString);
        ProcessadorPacote processador = new PacoteParser(new PacoteFactory()).identificarPacote(hexString);

        Pacote pacote = processador.processar(hexString);
        System.out.println(pacote);
    }
}
