package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleCursoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.*;

import java.util.List;
import java.util.Objects;

public class AlunoNapneOutputDTO extends AlunoOutputDTO{
    private String condicao;
    private String laudo;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;
    private String situacao;

    public AlunoNapneOutputDTO() {super();
    }

    public AlunoNapneOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<SimpleComentarioDTO> comentarios, List<SimplePublicacaoDTO> publicacaoEntities, List<ConfigAcessibilidadeEntity> config, SimpleCursoDTO cursoDTO, String condicao, String laudo, String necessidadeEspecial, String necessidadeEscolar, String acompanhamento, String situacao) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config, cursoDTO);
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
        AlunoNapneOutputDTO that = (AlunoNapneOutputDTO) o;
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
