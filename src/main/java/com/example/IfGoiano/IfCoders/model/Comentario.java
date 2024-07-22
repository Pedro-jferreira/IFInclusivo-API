package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.ComentarioId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Comments")
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComentarioId id = new ComentarioId();


    @NotNull
    private String content;
    @NotNull
    private LocalDateTime localDateTime;

    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "comentario_pai_publicacao_id", referencedColumnName = "publicacao_id"),
            @JoinColumn(name = "comentario_pai_usuario_id", referencedColumnName = "usuario_id")
    })
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "id.comentario",cascade = CascadeType.ALL)
    private List<ResolveuProblema>resolveuProblemas = new ArrayList<>();

    @OneToMany(mappedBy = "comentarioPai",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentariosFilhos = new ArrayList<>();

    public Comentario() {
    }

    public Comentario(String content, Publicacao publicacao, Usuario usuario) {
        this.content = content;
        this.id.setUsuario(usuario);
        this.id.setPublicacao(publicacao);
        this.localDateTime = LocalDateTime.now();
    }

    public Comentario(String content, Publicacao publicacao, Comentario comentarioPai, List<ResolveuProblema> resolveuProblemas, List<Comentario> comentariosFilhos) {
        this.content = content;
        this.id.setPublicacao(publicacao);
        this.comentarioPai = comentarioPai;
        this.resolveuProblemas = resolveuProblemas;
        this.comentariosFilhos = comentariosFilhos;
    }

    public ComentarioId getId() {
        return id;
    }

    public void setId(ComentarioId id) {
        this.id = id;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public  Publicacao getPublicacao() {
        return this.id.getPublicacao();
    }

    public void setPublicacao( Publicacao publicacao) {
        this.id.setPublicacao(publicacao);
    }

    public  Usuario getUsuario() {
        return this.id.getUsuario();
    }

    public void setUsuario( Usuario usuario) {
        this.id.setUsuario(usuario);
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

    public void addResolveuProblemaToComment(ResolveuProblema resolveuProblema){
        resolveuProblema.setComentario(this);
        getResolveuProblemas().add(resolveuProblema);
    }
    public void removeResolveuProblemaFromComment(ResolveuProblema resolveuProblema){
        resolveuProblema.setComentario(null);
        getResolveuProblemas().remove(resolveuProblema);
    }
    public void addChildCommentToComment(Comentario comentario){
        comentario.setComentarioPai(this);
        getComentariosFilhos().add(comentario);
    }

    public void removeChildCommentFromComment(Comentario comentario){
        comentario.setComentarioPai(null);
        getComentariosFilhos().remove(comentario);
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
