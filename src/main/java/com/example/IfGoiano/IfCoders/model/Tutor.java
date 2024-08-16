package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Tutor extends Usuario{

    private String especialidade;

    public Tutor () {

    }

    public Tutor(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade, String especialidade) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
