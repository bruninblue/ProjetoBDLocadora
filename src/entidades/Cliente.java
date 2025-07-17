package entidades;

public class Cliente {
    private String cpf;
    private String nomeCompleto;
    private String numTelefone;
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getNumTelefone() {
        return numTelefone;
    }
    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }
}
