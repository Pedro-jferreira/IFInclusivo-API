package com.example.IfGoiano.IfCoders.model.PK;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Usuario;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResolveuProblemaId implements Serializable {

    @ManyToOne()
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "comentario_publicacao_id", referencedColumnName = "publicacao_id"),
            @JoinColumn(name = "comentario_usuario_id", referencedColumnName = "usuario_id")
    })
    private Comentario comentario;
    @ManyToOne()
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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
        if (!(o instanceof ResolveuProblemaId that)) return false;
        return Objects.equals(getComentario(), that.getComentario()) && Objects.equals(getUsuario(), that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComentario(), getUsuario());
    }
}
