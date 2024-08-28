package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AlunoEntity extends UsuarioEntity {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;


    public AlunoEntity() { super();   }

    public AlunoEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities, List<LibrasEntity> librasEntities, List<MessageEntity> userEnvia, List<MessageEntity> userRecebe, List<PublicacaoEntity> likes, List<ComentarioEntity> useful, CursoEntity curso) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, librasEntities, userEnvia, userRecebe, likes, useful);
        this.curso = curso;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public void setCurso(CursoEntity cursoEntity) {
        this.curso = cursoEntity;
    }

}

