package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class AlunoNapneSimplificadoDTO extends AlunoSimplificadoDTO{
    private String condicao;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;


    public AlunoNapneSimplificadoDTO() {    }

    public AlunoNapneSimplificadoDTO(String condicao, String necessidadeEspecial, String necessidadeEscolar, String acompanhamento) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlunoNapneSimplificadoDTO that = (AlunoNapneSimplificadoDTO) o;
        return Objects.equals(condicao, that.condicao) && Objects.equals(necessidadeEspecial, that.necessidadeEspecial)
                && Objects.equals(necessidadeEscolar, that.necessidadeEscolar) && Objects.equals(acompanhamento, that.acompanhamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), condicao, necessidadeEspecial, necessidadeEscolar, acompanhamento);
    }
}
