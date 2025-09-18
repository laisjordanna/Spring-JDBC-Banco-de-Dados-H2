package br.com.catolica.crud_spring.model.dto.request;

import br.com.catolica.crud_spring.model.Contato;

public class EntregadorRequestDTO {
    private String nome;
    private Contato contato;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}