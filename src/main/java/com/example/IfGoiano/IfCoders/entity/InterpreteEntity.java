package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table
public class InterpreteEntity extends TutorEntity {

    @NotNull    @Column(nullable = false)
    private Double salary;

    @ManyToMany
    private List<LibrasEntity> libras = new ArrayList<>();


    public InterpreteEntity() {
    }

    public InterpreteEntity(String nome, String login, String senha, Long matricula, String biografia,
                            ConfigAcessibilidadeEntity configAcessibilidadeEntity, String especialidade, Double salary) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity, especialidade);
        this.salary = salary;
   }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterpreteEntity that = (InterpreteEntity) o;
        return Objects.equals(salary, that.salary) && Objects.equals(libras, that.libras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, libras);
    }
}
