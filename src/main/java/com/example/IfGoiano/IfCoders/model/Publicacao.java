package com.example.IfGoiano.IfCoders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Publications")
public class Publicacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime localDateTime = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;


    @OneToMany(mappedBy = "id.publicacao",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();


    @OneToMany(mappedBy = "id.publicacao",cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    public Publicacao() {
    }

    public Publicacao(String text, String urlVideo, String urlFoto, Usuario usuario) {
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.usuario = usuario;
    }

    public Publicacao(String text, String urlVideo, String urlFoto, LocalDateTime localDateTime, Usuario usuario, List<Like> likes, List<Comentario> comentarios) {
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.localDateTime = localDateTime;
        this.usuario = usuario;
        this.likes = likes;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public  Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario( Usuario usuario) {
        this.usuario = usuario;
    }

    public  List<Like> getLikes() {
        return likes;
    }

    public void setLikes( List<Like> likes) {
        this.likes = likes;
    }

    public @NotNull List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios( List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }
    public void addLikeToPublicacao(Like like){
        like.setPublicacao(this);
        getLikes().add(like);
    }
    public void removeLikeFromPublicacao(Like like){
        like.setPublicacao(null);
        getLikes().remove(like);
    }
    public void addCommentToPublicacao(Comentario comentario){
        comentario.setPublicacao(this);
        getComentarios().add(comentario);
    }
    public void removeCommentFromPublicacao(Comentario comentario){
        comentario.setPublicacao(null);
        getComentarios().remove(comentario);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publicacao that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getText(), that.getText()) && Objects.equals(getUrlVideo(), that.getUrlVideo()) && Objects.equals(getUrlFoto(), that.getUrlFoto()) && Objects.equals(getLocalDateTime(), that.getLocalDateTime()) && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getLikes(), that.getLikes()) && Objects.equals(getComentarios(), that.getComentarios());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getUrlVideo(), getUrlFoto(), getLocalDateTime(), getUsuario(), getLikes(), getComentarios());
    }
}
