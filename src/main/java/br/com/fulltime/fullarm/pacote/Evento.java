package br.com.fulltime.fullarm.pacote;

public class Evento extends PacoteGenerico {
    private TipoConexao tipoConexao;
    private String conta;
    private String contactId;
    private String qualificador;
    private String codigoEvento;
    private String particao;
    private String argumento;
    private String checksum;

    public Evento(TipoConexao tipoConexao, String conta,
                  String contactId, String qualificador, String codigoEvento,
                  String particao, String argumento, String checksum) {
        super(TipoPacote.EVENTO);
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
        return String.format("""
                %s {
                  Conexão: %s
                  Conta: %s
                  ContactId: %s
                  Qualificador: %s
                  Código do Evento: %s
                  Partição: %s
                  Argumento: %s
                  Checksum: %s
                }
                """, this.tipo, this.tipoConexao, this.conta, this.contactId,
                this.qualificador, this.codigoEvento, this.particao, this.argumento, this.checksum);
    }
}
