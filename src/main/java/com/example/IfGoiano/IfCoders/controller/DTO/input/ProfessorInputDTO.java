package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class ProfessorInputDTO extends UsuarioInputDTO{
    private String formacao;

    public ProfessorInputDTO() {    }

    public ProfessorInputDTO(String nome, String login, String senha, Long matricula, String biografia,
                             ConfigAcessibilidadeEntity configAcessibilidadeEntity, String formacao) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
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
        ProfessorInputDTO that = (ProfessorInputDTO) o;
        return Objects.equals(formacao, that.formacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formacao);
    }
}
