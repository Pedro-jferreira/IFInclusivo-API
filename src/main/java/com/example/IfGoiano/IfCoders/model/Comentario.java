package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Comments")
public class Comentario implements Serializable {
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
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentariosFilhos = new ArrayList<>();

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL)
    private List<ResolveuProblema> resolveuProblemas = new ArrayList<>();

    public Comentario() {
    }

    public Comentario(Long id, LocalDateTime localDateTime, UsuarioEntity usuario, Publicacao publicacao, Comentario comentarioPai, String content, List<ResolveuProblema> resolveuProblemas, List<Comentario> comentariosFilhos) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.usuario = usuario;
        this.publicacao = publicacao;
        this.comentarioPai = comentarioPai;
        this.content = content;
        this.resolveuProblemas = resolveuProblemas;
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

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public List<ResolveuProblema> getResolveuProblemas() {
        return resolveuProblemas;
    }

    public void setResolveuProblemas(List<ResolveuProblema> resolveuProblemas) {
        this.resolveuProblemas = resolveuProblemas;
    }

    public List<Comentario> getComentariosFilhos() {
        return comentariosFilhos;
    }

    public void setComentariosFilhos(List<Comentario> comentariosFilhos) {
        this.comentariosFilhos = comentariosFilhos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario)) return false;
        Comentario that = (Comentario) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(publicacao, that.publicacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, publicacao);
    }
}
