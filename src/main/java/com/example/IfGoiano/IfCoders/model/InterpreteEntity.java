package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class InterpreteEntity extends Tutor {

    private Double salary;

    public InterpreteEntity() {
    }

    public InterpreteEntity(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade, String especialidade, Double salary) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade, especialidade);
        this.salary = salary;
    }
}
