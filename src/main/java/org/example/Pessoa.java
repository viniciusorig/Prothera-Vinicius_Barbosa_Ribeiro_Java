package org.example;

import java.util.List;

public class Pessoa {
    private int id;
    private String nome;
    private int idade;
    private List<Documentos> documentos;

    // Getters e Setters (Alt + Insert para gerar)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public List<Documentos> getDocumentos() { return documentos; }
    public void setDocumentos(List<Documentos> documentos) { this.documentos = documentos; }

    @Override
    public String toString() {
        return nome + " (" + idade + ")";
    }
}