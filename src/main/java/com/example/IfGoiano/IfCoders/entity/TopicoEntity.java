package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topics")
public class TopicoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorEntity;

    @NotNull
    private String tema;

    @NotNull
    private String descripcion;

    @NotNull
    private String categoria;

    @OneToMany(mappedBy = "topico")
    private List<PublicacaoEntity> publicacoes = new ArrayList<>();




    public TopicoEntity() {
    }

    public TopicoEntity(Long id, ProfessorEntity professorEntity, String tema, String descripcion, String categoria, List<PublicacaoEntity> publicacoes) {
        this.id = id;
        this.professorEntity = professorEntity;
        this.tema = tema;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.publicacoes = publicacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getTema() {
        return tema;
    }

    public void setTema(@NotNull String tema) {
        this.tema = tema;
    }

    public @NotNull String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotNull String categoria) {
        this.categoria = categoria;
    }

    public List<PublicacaoEntity> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<PublicacaoEntity> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public ProfessorEntity getProfessorEntity() {
        return professorEntity;
    }

    public void setProfessorEntity(ProfessorEntity professorEntity) {
        this.professorEntity = professorEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicoEntity)) return false;
        TopicoEntity topicoEntity = (TopicoEntity) o;
        return Objects.equals(getId(), topicoEntity.getId()) && Objects.equals(getTema(), topicoEntity.getTema()) && Objects.equals(getDescripcion(), topicoEntity.getDescripcion()) && Objects.equals(getCategoria(), topicoEntity.getCategoria()) && Objects.equals(getPublicacoes(), topicoEntity.getPublicacoes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTema(), getDescripcion(), getCategoria(), getPublicacoes());
    }
}
