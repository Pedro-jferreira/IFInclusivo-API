package com.example.IfGoiano.IfCoders.model.PK;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikeId implements Serializable {
    private Long usuarioId;
    private Long publicacaoId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacaoId() {
        return publicacaoId;
    }

    public void setPublicacaoId(Long publicacaoId) {
        this.publicacaoId = publicacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeId)) return false;
        LikeId likeId = (LikeId) o;
        return Objects.equals(getUsuarioId(), likeId.getUsuarioId()) && Objects.equals(getPublicacaoId(), likeId.getPublicacaoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuarioId(), getPublicacaoId());
    }
}
