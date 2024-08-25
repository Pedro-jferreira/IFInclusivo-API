package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class ProfessorInputDTO extends UsuarioInputDTO{
    private String formacao;

    public ProfessorInputDTO() {    }

    public ProfessorInputDTO(Long id,String nome, String login, String senha, Long matricula, String biografia,
                             ConfigAcessibilidadeEntity configAcessibilidadeEntity, String formacao) {
        super(id,nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

}
