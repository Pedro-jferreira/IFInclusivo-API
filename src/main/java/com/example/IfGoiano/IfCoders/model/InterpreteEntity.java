package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "interprete")
public class InterpreteEntity extends TultorEntity{

    private Double salary;

    @ManyToMany
    private List<LibrasEntity> libras = new ArrayList<>();

    public InterpreteEntity() {
    }


    public InterpreteEntity(Long id, String nome, String especialidade, String login, String senha, Long matricula, String biografia) {
        super(id, nome, especialidade, login, senha, matricula, biografia);
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<LibrasEntity> getLibras() {
        return libras;
    }

    public void setLibras(List<LibrasEntity> libras) {
        this.libras = libras;
    }

    @Override
    public String toString() {
        return "InterpreteEntity{" +
                "salary=" + salary +
                ", libras=" + libras +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterpreteEntity that = (InterpreteEntity) o;
        return Objects.equals(salary, that.salary) && Objects.equals(libras, that.libras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, libras);
    }
}
