package br.com.catolica.crud_spring.model;

public class Entregador {
    private int id;
    private String nome;
    private int idade;
    private int contatoId;

    public Entregador(){
    }

    public Entregador(int id, String nome, int idade, int contatoId) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.contatoId = contatoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getContatoId() {
        return contatoId;
    }

    public void setContatoId(int contatoId) {
        this.contatoId = contatoId;
    }
}
