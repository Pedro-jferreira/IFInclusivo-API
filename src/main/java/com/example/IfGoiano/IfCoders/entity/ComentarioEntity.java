package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Comments")
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime localDateTime = LocalDateTime.now();

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "publicacao_id", nullable = false)
    private PublicacaoEntity publicacaoEntity;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private ComentarioEntity comentarioPai;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentariosFilhos = new ArrayList<>();


    public ComentarioEntity() {
    }

    public ComentarioEntity(Long id, LocalDateTime localDateTime, UsuarioEntity usuario, PublicacaoEntity publicacaoEntity, ComentarioEntity comentarioPai, String content,  List<ComentarioEntity> comentariosFilhos) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.usuario = usuario;
        this.publicacaoEntity = publicacaoEntity;
        this.comentarioPai = comentarioPai;
        this.content = content;
        this.comentariosFilhos = comentariosFilhos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(@NotNull LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PublicacaoEntity getPublicacao() {
        return publicacaoEntity;
    }

    public void setPublicacao(PublicacaoEntity publicacaoEntity) {
        this.publicacaoEntity = publicacaoEntity;
    }

    public ComentarioEntity getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(ComentarioEntity comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public List<ComentarioEntity> getComentariosFilhos() {
        return comentariosFilhos;
    }

    public void setComentariosFilhos(List<ComentarioEntity> comentariosFilhos) {
        this.comentariosFilhos = comentariosFilhos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComentarioEntity)) return false;
        ComentarioEntity that = (ComentarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(publicacaoEntity, that.publicacaoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, publicacaoEntity);
    }
}
