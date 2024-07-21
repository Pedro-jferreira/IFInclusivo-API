package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "professor")
public class ProfessorEntity extends Usuario {

    private String formacao;


    public ProfessorEntity() {
    }


    public ProfessorEntity( String nome, String login, String senha, Long matricula, String biografia, String formacao) {
        super(nome, login, senha, matricula, biografia);
        this.formacao = formacao;
    }


    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
