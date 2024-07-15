package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ResolveuProblema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @NotNull
    @JoinColumn(name = "comentario_id")

    private Comentario comentario;
    @ManyToOne()
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ResolveuProblema() {
    }

    public ResolveuProblema(Comentario comentario, Usuario usuario) {
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResolveuProblema that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getComentario(), that.getComentario()) && Objects.equals(getUsuario(), that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getComentario(), getUsuario());
    }
}
