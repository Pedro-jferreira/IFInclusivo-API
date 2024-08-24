package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import java.util.List;
import java.util.Objects;

public class ProfessorOutputDTO extends UsuarioOutputDTO {
    private String formacao;


    public ProfessorOutputDTO() {    }

    public ProfessorOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia,
                              ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios,
                              List<PublicacaoEntity> publicacaoEntities, List<ConfigAcessibilidadeEntity> config, String formacao) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
        this.formacao = formacao;
    }


    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProfessorOutputDTO that = (ProfessorOutputDTO) o;
        return Objects.equals(formacao, that.formacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formacao);
    }
}
