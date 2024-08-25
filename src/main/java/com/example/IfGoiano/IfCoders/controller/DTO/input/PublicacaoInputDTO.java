package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import java.util.Objects;

public class PublicacaoInputDTO {
    private Long id;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private SimpleUsuarioDTO usuario;
    private SimpleTopicoDTO topico;

    public PublicacaoInputDTO() {
    }

    public PublicacaoInputDTO(Long id, String text, String urlVideo, String urlFoto, SimpleUsuarioDTO usuario, SimpleTopicoDTO topico) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.usuario = usuario;
        this.topico = topico;
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

    public SimpleUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(SimpleUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public SimpleTopicoDTO getTopicoEntity() {
        return topico;
    }

    public void setTopicoEntity(SimpleTopicoDTO topico) {
        this.topico = topico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicacaoInputDTO that = (PublicacaoInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario);
    }
}
