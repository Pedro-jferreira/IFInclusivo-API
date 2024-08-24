package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;

import java.util.Objects;

public class AlunoNapneInputDTO extends AlunoInputDTO{

    private String condicao;
    private String laudo;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;
    private String situacao;


    public AlunoNapneInputDTO() {    }

    public AlunoNapneInputDTO(String nome, String login, String senha, Long matricula, String biografia,
                              ConfigAcessibilidadeEntity configAcessibilidadeEntity, CursoEntity cursoEntity,
                              String condicao, String laudo, String necessidadeEspecial, String necessidadeEscolar,
                              String acompanhamento, String situacao) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity, cursoEntity);
        this.condicao = condicao;
        this.laudo = laudo;
        this.necessidadeEspecial = necessidadeEspecial;
        this.necessidadeEscolar = necessidadeEscolar;
        this.acompanhamento = acompanhamento;
        this.situacao = situacao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlunoNapneInputDTO that = (AlunoNapneInputDTO) o;
        return Objects.equals(condicao, that.condicao) && Objects.equals(laudo, that.laudo) &&
                Objects.equals(necessidadeEspecial, that.necessidadeEspecial) &&
                Objects.equals(necessidadeEscolar, that.necessidadeEscolar) &&
                Objects.equals(acompanhamento, that.acompanhamento) && Objects.equals(situacao, that.situacao);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), condicao, laudo, necessidadeEspecial, necessidadeEscolar, acompanhamento, situacao);
    }
}
