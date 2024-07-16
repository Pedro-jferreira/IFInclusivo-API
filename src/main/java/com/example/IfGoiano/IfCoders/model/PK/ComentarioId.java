package com.example.IfGoiano.IfCoders.model.PK;

import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Usuario;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComentarioId implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Publicacao getPublicacao() {
        return publicacao;
    }
    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
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
        if (!(o instanceof ComentarioId that)) return false;
        return Objects.equals(getPublicacao(), that.getPublicacao()) && Objects.equals(getUsuario(), that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublicacao(), getUsuario());
    }


}
