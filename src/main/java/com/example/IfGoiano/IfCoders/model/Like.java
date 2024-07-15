package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    public Like() {
    }

    public Like(Usuario usuario, Publicacao publicacao) {
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull Usuario usuario) {
        this.usuario = usuario;
    }

    public @NotNull Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(@NotNull Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like like)) return false;
        return Objects.equals(getId(), like.getId()) && Objects.equals(getUsuario(), like.getUsuario()) && Objects.equals(getPublicacao(), like.getPublicacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getPublicacao());
    }
}
