package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.ResolveuProblemaId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SolvedProblems")
public class ResolveuProblema implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ResolveuProblemaId id = new ResolveuProblemaId();


    public ResolveuProblema() {
    }

    public ResolveuProblema(Comentario comentario, Usuario usuario) {
        this.id.setComentario(comentario);
        this.id.setUsuario(usuario);
    }

    public Comentario getComentario() {
        return this.id.getComentario();
    }

    public void setComentario(Comentario comentario) {
        this.id.setComentario(comentario);
    }

    public Usuario getUsuario() {
        return this.id.getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.id.setUsuario(usuario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResolveuProblema that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
