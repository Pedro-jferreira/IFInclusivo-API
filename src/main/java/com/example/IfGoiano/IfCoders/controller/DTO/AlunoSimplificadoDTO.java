package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.CursoEntity;

import java.util.Objects;

public class AlunoSimplificadoDTO extends UsuarioSimplificadoDTO {
    private CursoEntity cursoEntity;


    public AlunoSimplificadoDTO() {   }

    public AlunoSimplificadoDTO(String nome, Long matricula, CursoEntity cursoEntity) {
        super(nome, matricula);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlunoSimplificadoDTO that = (AlunoSimplificadoDTO) o;
        return Objects.equals(cursoEntity, that.cursoEntity);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cursoEntity);
    }
}
