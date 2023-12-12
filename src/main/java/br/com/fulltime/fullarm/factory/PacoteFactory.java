package br.com.fulltime.fullarm.factory;

import br.com.fulltime.fullarm.pacote.*;

public class PacoteFactory {
    public Pacote createPacote(String identificador) {
        switch (identificador) {
            case "F7":
                return new KeepAlive(TipoPacote.KEEP_ALIVE);
            case "B0":
                return new Evento(TipoPacote.EVENTO);
            case "FE":
                return new Ack(TipoPacote.ACK);
            case "94":
                return new Autenticacao(TipoPacote.AUTENTICACAO);
            case "E0":
                return new Nack(TipoPacote.NACK,"Formato de pacote inválido");
            case "E1":
                return new Nack(TipoPacote.NACK,"Senha incorreta");
            case "E2":
                return new Nack(TipoPacote.NACK,"Comando inválido");
            case "E3":
                return new Nack(TipoPacote.NACK,"Central não particionada");
            case "E4":
                return new Nack(TipoPacote.NACK,"Zonas abertas");
            case "E5":
                return new Nack(TipoPacote.NACK,"Comando descontinuado");
            case "E6":
                return new Nack(TipoPacote.NACK,"Usuário sem permissão para bypass");
            case "E7":
                return new Nack(TipoPacote.NACK,"Usuário sem permissão para desativar");
            case "E8":
                return new Nack(TipoPacote.NACK,"Bypass não permitido com a central ativada");
            default:
                throw new IllegalArgumentException("Identificador de pacote desconhecido: " + identificador);
        }
    }
}
