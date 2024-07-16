package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.LikeId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Likes")
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private LikeId id;



    public Like() {
    }

    public Like(Usuario usuario, Publicacao publicacao) {
        this.id.setUsuario(usuario);
        this.id.setPublicacao(publicacao);
    }



    public @NotNull Usuario getUsuario() {
        return this.id.getUsuario();
    }

    public void setUsuario(@NotNull Usuario usuario) {
        this.id.setUsuario(usuario);
    }

    public @NotNull Publicacao getPublicacao() {
        return this.id.getPublicacao();
    }

    public void setPublicacao(@NotNull Publicacao publicacao) {
        this.id.setPublicacao(publicacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like like)) return false;
        return Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
