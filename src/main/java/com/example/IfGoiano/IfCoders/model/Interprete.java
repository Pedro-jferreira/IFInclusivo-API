package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table
public class Interprete extends Tutor {

    private Double salary;

    @ManyToMany
    private List<Libras> libras = new ArrayList<>();

    public Interprete() {
    }

    public Interprete(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade, String especialidade, Double salary) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade, especialidade);
        this.salary = salary;

   }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Libras> getLibras() {
        return libras;
    }

    public void setLibras(List<Libras> libras) {
        this.libras = libras;
    }
}
