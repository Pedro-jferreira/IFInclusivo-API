package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;


import java.util.Objects;

public class PublicacaoInputDTO {
    private Long id;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private SimpleTopicoDTO topico;

    public PublicacaoInputDTO() {
    }

    public PublicacaoInputDTO(Long id, String text, String urlVideo, String urlFoto, SimpleTopicoDTO topico) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;

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


    public SimpleTopicoDTO getTopico() {
        return topico;
    }

    public void setTopico(SimpleTopicoDTO topico) {
        this.topico = topico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicacaoInputDTO that = (PublicacaoInputDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PublicacaoInputDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", topico=" + topico +
                '}';
    }
}
