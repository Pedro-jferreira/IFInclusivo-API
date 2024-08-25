package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PublicacaoOutputDTO {

    private Long id;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime dataCriacao;
    private SimpleUsuarioDTO usuario;
    private SimpleTopicoDTO topico;
    private List<SimpleComentarioDTO> comentarios;
    private List<SimpleUsuarioDTO> likeBy;

    public PublicacaoOutputDTO() {
    }

    public PublicacaoOutputDTO(Long id, String text, String urlVideo, String urlFoto, LocalDateTime dataCriacao, SimpleUsuarioDTO usuario, SimpleTopicoDTO topico, List<SimpleComentarioDTO> comentarios, List<SimpleUsuarioDTO> likeBy) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        this.topico = topico;
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

    public SimpleUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(SimpleUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public SimpleTopicoDTO getTopico() {
        return topico;
    }

    public void setTopico(SimpleTopicoDTO topico) {
        this.topico = topico;
    }

    public List<SimpleComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<SimpleComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<SimpleUsuarioDTO> getLikeBy() {
        return likeBy;
    }

    public void setLikeBy(List<SimpleUsuarioDTO> likeBy) {
        this.likeBy = likeBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicacaoOutputDTO that = (PublicacaoOutputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario);
    }

    @Override
    public String toString() {
        return "PublicacaoOutputDTO{" +
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
