package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.LikeId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Likes")
public class Like implements Serializable {
    @EmbeddedId
    private LikeId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @MapsId("publicacaoId")
    @JoinColumn(name = "publicacao_id", nullable = false)
    private Publicacao publicacao;

    private LocalDateTime dataHora = LocalDateTime.now();

    public Like() {
    }

    public Like(LikeId id, UsuarioEntity usuario, Publicacao publicacao) {
        this.id = id;
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
