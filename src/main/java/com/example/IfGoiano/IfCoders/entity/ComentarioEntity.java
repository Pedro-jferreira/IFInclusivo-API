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
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacao_id", nullable = false)
    private PublicacaoEntity publicacaoEntity;


    @NotNull
    private String content;

    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private ComentarioEntity comentarioPai;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentariosFilhos = new ArrayList<>();

    @ManyToMany(mappedBy = "useful")
    private List<UsuarioEntity> usefulBy  = new ArrayList<>();


    public ComentarioEntity() {
    }

    public ComentarioEntity(Long id, UsuarioEntity usuario, PublicacaoEntity publicacaoEntity, String content, LocalDateTime dataCriacao, ComentarioEntity comentarioPai, List<ComentarioEntity> comentariosFilhos, List<UsuarioEntity> usefulBy) {
        this.id = id;
        this.usuario = usuario;
        this.publicacaoEntity = publicacaoEntity;
        this.content = content;
        this.dataCriacao = dataCriacao;
        this.comentarioPai = comentarioPai;
        this.comentariosFilhos = comentariosFilhos;
        this.usefulBy = usefulBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public @NotNull PublicacaoEntity getPublicacaoEntity() {
        return publicacaoEntity;
    }

    public void setPublicacaoEntity(@NotNull PublicacaoEntity publicacaoEntity) {
        this.publicacaoEntity = publicacaoEntity;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public @NotNull LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(@NotNull LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ComentarioEntity getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(ComentarioEntity comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public List<ComentarioEntity> getComentariosFilhos() {
        return comentariosFilhos;
    }

    public void setComentariosFilhos(List<ComentarioEntity> comentariosFilhos) {
        this.comentariosFilhos = comentariosFilhos;
    }

    public List<UsuarioEntity> getUsefulBy() {
        return usefulBy;
    }

    public void setUsefulBy(List<UsuarioEntity> usefulBy) {
        this.usefulBy = usefulBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioEntity that = (ComentarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) &&
                Objects.equals(publicacaoEntity, that.publicacaoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, publicacaoEntity);
    }
}