package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Publications")
public class PublicacaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime dataCriacao = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private TopicoEntity topico;

    @OneToMany(mappedBy = "publicacaoEntity",cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @ManyToMany(mappedBy = "likes")
    private List<UsuarioEntity> likeBy = new ArrayList<>();

    public PublicacaoEntity() {
    }

    public PublicacaoEntity(Long id, String text, String urlVideo, String urlFoto, LocalDateTime dataCriacao, UsuarioEntity usuario, TopicoEntity topicoEntity, List<ComentarioEntity> comentarios, List<UsuarioEntity> likeBy) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        this.topico = topicoEntity;
        this.comentarios = comentarios;
        this.likeBy = likeBy;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public TopicoEntity getTopico() {
        return topico;
    }

    public void setTopico(TopicoEntity topico) {
        this.topico = topico;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public List<UsuarioEntity> getLikeBy() {
        return likeBy;
    }

    public void setLikeBy(List<UsuarioEntity> likeBy) {
        this.likeBy = likeBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicacaoEntity that = (PublicacaoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario);
    }

    @Override
    public String toString() {
        return "PublicacaoEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", usuario=" + usuario +
                ", topico=" + topico +
                ", comentarios=" + comentarios +
                ", likeBy=" + likeBy +
                '}';
    }
}
