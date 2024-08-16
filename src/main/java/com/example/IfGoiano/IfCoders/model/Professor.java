package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name= "professor")
public class Professor extends Usuario {

    private String formacao;

    public Professor() {
    }

    public Professor(String formacao) {
        this.formacao = formacao;
    }

    public Professor(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade, String formacao) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade);
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
