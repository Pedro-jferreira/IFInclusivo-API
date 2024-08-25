package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.CursoEntity;

public class SimpleAlunoNapneDTO extends SimpleAlunoDTO {
    private String condicao;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;


    public SimpleAlunoNapneDTO() {    }


    public SimpleAlunoNapneDTO(Long id, String nome, Long matricula, SimpleCursoDTO cursoEntity, String condicao, String necessidadeEspecial, String necessidadeEscolar, String acompanhamento) {
        super(id, nome, matricula, cursoEntity);
        this.condicao = condicao;
        this.necessidadeEspecial = necessidadeEspecial;
        this.necessidadeEscolar = necessidadeEscolar;
        this.acompanhamento = acompanhamento;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    public void setNecessidadeEspecial(String necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public String getNecessidadeEscolar() {
        return necessidadeEscolar;
    }

    public void setNecessidadeEscolar(String necessidadeEscolar) {
        this.necessidadeEscolar = necessidadeEscolar;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(String acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

}
