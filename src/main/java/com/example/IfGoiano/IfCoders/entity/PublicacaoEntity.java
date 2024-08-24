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
    private LocalDateTime localDateTime = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;


    @ManyToOne
    @JoinColumn(name = "topico_id")
    private TopicoEntity topicoEntity;





    @OneToMany(mappedBy = "publicacaoEntity",cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    public PublicacaoEntity() {
    }

    public PublicacaoEntity(Long id, String text, String urlVideo, String urlFoto, LocalDateTime localDateTime, UsuarioEntity usuario, TopicoEntity topicoEntity, List<ComentarioEntity> comentarios) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.localDateTime = localDateTime;
        this.usuario = usuario;
        this.topicoEntity = topicoEntity;
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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public TopicoEntity getTopico() {
        return topicoEntity;
    }

    public void setTopico(TopicoEntity topicoEntity) {
        this.topicoEntity = topicoEntity;
    }


    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicacaoEntity)) return false;
        PublicacaoEntity that = (PublicacaoEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getText(), that.getText()) && Objects.equals(getUrlVideo(), that.getUrlVideo()) && Objects.equals(getUrlFoto(), that.getUrlFoto()) && Objects.equals(getLocalDateTime(), that.getLocalDateTime()) && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getTopico(), that.getTopico())  && Objects.equals(getComentarios(), that.getComentarios());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getUrlVideo(), getUrlFoto(), getLocalDateTime(), getUsuario(), getTopico(),  getComentarios());
    }
}
