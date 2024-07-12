package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class InterpreteEntity extends TultorEntity{

    private Double salary;

    public InterpreteEntity() {
    }

    public InterpreteEntity(Long id, String nome, String especialidade, String login, String senha, Long matricula, String biografia) {
        super(id, nome, especialidade, login, senha, matricula, biografia);
    }

}
