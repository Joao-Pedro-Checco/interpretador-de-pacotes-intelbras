package br.com.fulltime.fullarm.pacote;

public class Evento extends Pacote {
    private TipoConexao tipoConexao;
    private String conta;
    private String contactId;
    private String qualificador;
    private String codigoEvento;
    private String particao;
    private String argumento;
    private String checksum;

    public Evento(TipoPacote tipo, TipoConexao tipoConexao, String conta,
                  String contactId, String qualificador, String codigoEvento,
                  String particao, String argumento, String checksum) {
        super(tipo);
        this.tipoConexao = tipoConexao;
        this.conta = conta;
        this.contactId = contactId;
        this.qualificador = qualificador;
        this.codigoEvento = codigoEvento;
        this.particao = particao;
        this.argumento = argumento;
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return this.tipo + " {" +
                "\n  Conexão: " + this.tipoConexao +
                "\n  Conta: " + this.conta +
                "\n  ContactId: " + this.contactId +
                "\n  Qualificador: " + this.qualificador +
                "\n  Código Evento: " + this.codigoEvento +
                "\n  Partição: " + this.particao +
                "\n  Argumento: " + this.argumento +
                "\n  Checksum: " + this.checksum +
                "\n}";
    }
}
