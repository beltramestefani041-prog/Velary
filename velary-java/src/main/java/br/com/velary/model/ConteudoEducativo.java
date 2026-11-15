package br.com.velary.model;

public class ConteudoEducativo {
    private long id;
    private long faseId;
    private String faseNome;
    private String titulo;
    private String resumo;
    private String tipo;
    private String link;

    public ConteudoEducativo() {
    }

    public ConteudoEducativo(long id, long faseId, String faseNome, String titulo,
                             String resumo, String tipo, String link) {
        this.id = id;
        this.faseId = faseId;
        this.faseNome = faseNome;
        this.titulo = titulo;
        this.resumo = resumo;
        this.tipo = tipo;
        this.link = link;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getFaseId() { return faseId; }
    public void setFaseId(long faseId) { this.faseId = faseId; }
    public String getFaseNome() { return faseNome; }
    public void setFaseNome(String faseNome) { this.faseNome = faseNome; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    @Override
    public String toString() {
        return "ConteudoEducativo{id=" + id + ", titulo='" + titulo + "', fase='" + faseNome + "'}";
    }
}
