package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;

import java.util.Objects;

public class AlunoInputDTO extends UsuarioInputDTO{
    private CursoEntity cursoEntity;


    public AlunoInputDTO() {    }

    public AlunoInputDTO(String nome, String login, String senha, Long matricula, String biografia,
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlunoInputDTO that = (AlunoInputDTO) o;
        return Objects.equals(cursoEntity, that.cursoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cursoEntity);
    }
}
