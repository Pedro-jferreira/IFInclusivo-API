package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import java.util.List;
import java.util.Objects;

public class AlunoOutputDTO extends UsuarioOutputDTO {
    private CursoEntity cursoEntity;


    public AlunoOutputDTO() {
    }
    public AlunoOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia,
                          ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios,
                          List<PublicacaoEntity> publicacaoEntities, List<ConfigAcessibilidadeEntity> config,
                          CursoEntity cursoEntity) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
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
        AlunoOutputDTO that = (AlunoOutputDTO) o;
        return Objects.equals(cursoEntity, that.cursoEntity);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cursoEntity);
    }
}
