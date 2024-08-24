package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class ProfessorSimplificadoDTO extends UsuarioSimplificadoDTO {
    private String formacao;


    public ProfessorSimplificadoDTO() {    }

    public ProfessorSimplificadoDTO(String nome, Long matricula, String formacao) {
        super(nome, matricula);
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
        ProfessorSimplificadoDTO that = (ProfessorSimplificadoDTO) o;
        return Objects.equals(formacao, that.formacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formacao);
    }
}
