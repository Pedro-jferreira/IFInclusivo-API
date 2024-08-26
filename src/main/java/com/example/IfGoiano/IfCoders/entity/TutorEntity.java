package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "Tutor")
public class TutorEntity extends UsuarioEntity {

    @NotNull    @Column(nullable = false)
    private String especialidade;


    public TutorEntity() {   super();  }

    public TutorEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities, List<PublicacaoEntity> likes, List<ComentarioEntity> useful, String especialidade) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities,  likes, useful);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
