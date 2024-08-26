package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleCursoDTO;
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


    public AlunoNapneInputDTO() {   super();  }

    public AlunoNapneInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, SimpleCursoDTO cursoDTO, String condicao, String laudo, String necessidadeEspecial, String necessidadeEscolar, String acompanhamento, String situacao) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, cursoDTO);
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

}
