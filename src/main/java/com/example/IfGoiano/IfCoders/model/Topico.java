package com.example.IfGoiano.IfCoders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topics")
public class Topico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tema;

    @NotNull
    private String descripcion;

    @NotNull
    private String categoria;

    @OneToMany(mappedBy = "topico")
    private List<Publicacao> publicacoes = new ArrayList<>();


    public Topico() {
    }

    public Topico(String tema, String descripcion, String categoria) {
        this.tema = tema;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topico topico)) return false;
        return Objects.equals(getId(), topico.getId()) && Objects.equals(getTema(), topico.getTema()) && Objects.equals(getDescripcion(), topico.getDescripcion()) && Objects.equals(getCategoria(), topico.getCategoria()) && Objects.equals(getPublicacoes(), topico.getPublicacoes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTema(), getDescripcion(), getCategoria(), getPublicacoes());
    }

    public void addPublicacao(Publicacao publicacao) {
    }

    public void removePublicacao(Publicacao publicacao) {

    }
}
