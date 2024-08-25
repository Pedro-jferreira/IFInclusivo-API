package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "professor")
public class ProfessorEntity extends UsuarioEntity {

    private String formacao;

    @OneToMany(mappedBy ="professorEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TopicoEntity> topicos = new ArrayList<>();

    public ProfessorEntity() { super();
    }

    public ProfessorEntity(String formacao) {
        this.formacao = formacao;
    }

    public ProfessorEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities, List<PublicacaoEntity> likes, List<ComentarioEntity> useful, String formacao, List<TopicoEntity> topicos) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, likes, useful);
        this.formacao = formacao;
        this.topicos = topicos;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public List<TopicoEntity> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<TopicoEntity> topicos) {
        this.topicos = topicos;
    }
}
