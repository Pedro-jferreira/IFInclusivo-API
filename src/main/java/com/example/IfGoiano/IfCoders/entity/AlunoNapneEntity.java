package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Aluno_napne")
public class AlunoNapneEntity extends AlunoEntity {

    @NotNull   @Column(nullable = false)
    private String condicao;
    @NotNull   @Column(nullable = false)
    private String laudo;
    @NotNull   @Column(nullable = false)
    private String necessidadeEspecial;
    @NotNull   @Column(nullable = false)
    private String necessidadeEscolar;
    @NotNull   @Column(nullable = false)
    private String acompanhamento;
    @NotNull   @Column(nullable = false)
    private String situacao;


    public AlunoNapneEntity() {  super();   }


    public AlunoNapneEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities,  List<PublicacaoEntity> likes, List<ComentarioEntity> useful, CursoEntity cursoEntity, String condicao, String laudo, String necessidadeEspecial, String necessidadeEscolar, String acompanhamento, String situacao) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, likes, useful, cursoEntity);
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
