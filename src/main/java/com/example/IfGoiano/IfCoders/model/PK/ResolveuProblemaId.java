package com.example.IfGoiano.IfCoders.model.PK;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResolveuProblemaId implements Serializable {

    private Long usuarioId;
    private Long comentarioId;

    public ResolveuProblemaId() {
    }

    public ResolveuProblemaId(Long usuarioId, Long comentarioId) {
        this.usuarioId = usuarioId;
        this.comentarioId = comentarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(Long comentarioId) {
        this.comentarioId = comentarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResolveuProblemaId)) return false;
        ResolveuProblemaId that = (ResolveuProblemaId) o;
        return Objects.equals(getUsuarioId(), that.getUsuarioId()) && Objects.equals(getComentarioId(), that.getComentarioId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuarioId(), getComentarioId());
    }
}
