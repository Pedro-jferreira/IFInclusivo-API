package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AlunoEntity extends UsuarioEntity {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity cursoEntity;


    public AlunoEntity() {    }

    public AlunoEntity(String nome, String login, String senha, Long matricula, String biografia,
                       ConfigAcessibilidadeEntity configAcessibilidadeEntity, CursoEntity cursoEntity) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
        this.cursoEntity = cursoEntity;
    }


    public CursoEntity getCurso() {
        return cursoEntity;
    }

    public void setCurso(CursoEntity cursoEntity) {
        this.cursoEntity = cursoEntity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o))
            return false;
        AlunoEntity aluno = (AlunoEntity) o;
        return Objects.equals(cursoEntity, aluno.cursoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cursoEntity);
    }
}

