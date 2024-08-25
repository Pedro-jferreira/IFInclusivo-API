package com.example.IfGoiano.IfCoders.controller.DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class SimplePublicacaoDTO {
    private Long id;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime dataCriacao;

    public SimplePublicacaoDTO() {
    }

    public SimplePublicacaoDTO(Long id, String text, String urlVideo, String urlFoto, LocalDateTime dataCriacao) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePublicacaoDTO that = (SimplePublicacaoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao);
    }
}
