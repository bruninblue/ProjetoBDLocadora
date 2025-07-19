package entidades;

public class Acervo {
    private int idAcervo;
    private int filmeId;
    public enum Situacao {
        DISPONIVEL, ALUGADO, DANIFICADO
    }
    private Situacao situacao;

    public int getIdAcervo() {
        return idAcervo;
    }
    public void setIdAcervo(int idAcervo) {
        this.idAcervo = idAcervo;
    }

    public int getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public Situacao getSituacao() {
        return situacao;
    }
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}