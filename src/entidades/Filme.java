package entidades;

import java.sql.Date;

public class Filme {
    private int id;
    private String título;
    private Date dataLancamento;
    private int genero;
    private float valor;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTítulo() {
        return título;
    }
    public void setTítulo(String título) {
        this.título = título;
    }
    public Date getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public int getGenero() {
        return genero;
    }
    public void setGenero(int genero) {
        this.genero = genero;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    
}
