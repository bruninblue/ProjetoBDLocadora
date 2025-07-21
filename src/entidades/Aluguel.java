package entidades;

import java.sql.Date;

public class Aluguel {
    private int id;
    private String clienteCpf;
    private int filmeId;
    private Date dataAluguel;
    private Date dataDevolucao;
    private float valorPagar;
    private float multa;
    private int pendente;
    private String cpfCliente;
    private String clienteNome;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getClienteCpf() {
        return clienteCpf;
    }
    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }
    public int getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }
    public Date getDataAluguel() {
        return dataAluguel;
    }
    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public float getValorPagar() {
        return valorPagar;
    }
    public void setValorPagar(float valorPagar) {
        this.valorPagar = valorPagar;
    }
    public float getMulta() {
        return multa;
    }
    public void setMulta(float multa) {
        this.multa = multa;
    }
    public int getPendente() {
        return pendente;
    }
    public void setPendente(int pendente) {
        this.pendente = pendente;
    }
    public String getCpfCliente() {
        return cpfCliente;
    }
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    public String getClienteNome() {
        return clienteNome;
    }
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    
}
