package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class InterpreteEntity extends Tutor {

    private Double salary;

    @ManyToMany
    private List<LibrasEntity> libras = new ArrayList<>();

    public InterpreteEntity() {
    }

    public InterpreteEntity(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade, String especialidade, Double salary) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade, especialidade);
        this.salary = salary;

   }
}
