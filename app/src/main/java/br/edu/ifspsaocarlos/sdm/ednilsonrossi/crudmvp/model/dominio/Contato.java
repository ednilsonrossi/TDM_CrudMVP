package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dominio;

public class Contato {
    private final int ID_DEFAULT = -1;
    private int id;
    private String apelido;
    private String nomeCompleto;

    public Contato(int id, String apelido, String nomeCompleto) {
        this.id = id;
        this.apelido = apelido;
        this.nomeCompleto = nomeCompleto;
    }

    public Contato(String apelido, String nomeCompleto) {
        this.apelido = apelido;
        this.nomeCompleto = nomeCompleto;
        this.id = ID_DEFAULT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
