package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AlunoEntity extends UsuarioEntity {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;


    public AlunoEntity() {    }

    public AlunoEntity(String nome, String login, String senha, Long matricula, String biografia,
                       ConfigAcessibilidade configAcessibilidade, Curso curso) {
        super(nome, login, senha, matricula, biografia, configAcessibilidade);
        this.curso = curso;
    }


    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o))
            return false;
        AlunoEntity aluno = (AlunoEntity) o;
        return Objects.equals(curso, aluno.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), curso);
    }
}

