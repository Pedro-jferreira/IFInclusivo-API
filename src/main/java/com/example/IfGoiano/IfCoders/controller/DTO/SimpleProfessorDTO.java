package com.example.IfGoiano.IfCoders.controller.DTO;

public class SimpleProfessorDTO extends SimpleUsuarioDTO {
    private String formacao;


    public SimpleProfessorDTO() {    }

    public SimpleProfessorDTO(Long id, String nome, Long matricula, String formacao) {
        super(id,nome, matricula);
        this.formacao = formacao;
    }


    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

}
