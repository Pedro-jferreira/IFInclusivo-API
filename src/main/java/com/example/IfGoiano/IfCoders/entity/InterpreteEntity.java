package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table
public class InterpreteEntity extends TutorEntity {

    @NotNull    @Column(nullable = false)
    private Double salary;

    @ManyToMany
    private List<LibrasEntity> libras = new ArrayList<>();


    public InterpreteEntity() { super();
    }

    public InterpreteEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities,  List<PublicacaoEntity> likes, List<ComentarioEntity> useful, String especialidade, Double salary, List<LibrasEntity> libras) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities,  likes, useful, especialidade);
        this.salary = salary;
        this.libras = libras;
    }


}
