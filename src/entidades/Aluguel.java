package entidades;

import java.sql.Date;

public class Aluguel {
    private int id;
    private int clienteId;
    private int filmeId;
    private Date dataAluguel;
    private Date dataDevolucao;
    private float valorPagar;
    private int pendente;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClienteId() {
        return clienteId;
    }
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
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
    public int getPendente() {
        return pendente;
    }
    public void setPendente(int pendente) {
        this.pendente = pendente;
    }

    
}
