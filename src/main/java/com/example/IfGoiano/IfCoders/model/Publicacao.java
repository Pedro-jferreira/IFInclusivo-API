package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @NotNull
    @OneToMany(mappedBy = "id.publicacao",cascade = CascadeType.ALL)
    private List<Like> likes;

    @NotNull
    @OneToMany(mappedBy = "id.publicacao",cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Publicacao() {
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

    public @NotNull Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull Usuario usuario) {
        this.usuario = usuario;
    }

    public @NotNull List<Like> getLikes() {
        return likes;
    }

    public void setLikes(@NotNull List<Like> likes) {
        this.likes = likes;
    }

    public @NotNull List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(@NotNull List<Comentario> comentarios) {
        this.comentarios = comentarios;
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
