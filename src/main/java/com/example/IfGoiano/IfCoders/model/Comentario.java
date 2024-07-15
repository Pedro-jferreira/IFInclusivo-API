package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @NotNull
    private LocalDateTime localDateTime = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "comentario_pai")
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "comentario",cascade = CascadeType.ALL)
    private List<ResolveuProblema>resolveuProblemas;

    @OneToMany(mappedBy = "comentarioPai",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentariosFilhos;

    public Comentario() {
    }

    public Comentario(String content, Publicacao publicacao, Comentario comentarioPai, List<ResolveuProblema> resolveuProblemas, List<Comentario> comentariosFilhos) {
        this.content = content;
        this.publicacao = publicacao;
        this.comentarioPai = comentarioPai;
        this.resolveuProblemas = resolveuProblemas;
        this.comentariosFilhos = comentariosFilhos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public @NotNull Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(@NotNull Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public @NotNull LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(@NotNull LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
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
        if (!(o instanceof Comentario that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getPublicacao(), that.getPublicacao()) && Objects.equals(getLocalDateTime(), that.getLocalDateTime()) && Objects.equals(getComentarioPai(), that.getComentarioPai()) && Objects.equals(getResolveuProblemas(), that.getResolveuProblemas()) && Objects.equals(getComentariosFilhos(), that.getComentariosFilhos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getContent(), getPublicacao(), getLocalDateTime(), getComentarioPai(), getResolveuProblemas(), getComentariosFilhos());
    }
}
