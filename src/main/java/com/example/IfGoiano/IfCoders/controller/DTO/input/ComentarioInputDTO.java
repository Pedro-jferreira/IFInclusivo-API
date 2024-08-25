package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import java.util.Objects;

public class ComentarioInputDTO {

    private Long id;
    private SimpleUsuarioDTO usuario;
    private SimplePublicacaoDTO publicacao;
    private String content;
    private SimpleComentarioDTO comentarioPai;

    public ComentarioInputDTO() {
    }

    public ComentarioInputDTO(Long id, SimpleUsuarioDTO usuario, SimplePublicacaoDTO publicacao, String content, SimpleComentarioDTO comentarioPai) {
        this.id = id;
        this.usuario = usuario;
        this.publicacao = publicacao;
        this.content = content;
        this.comentarioPai = comentarioPai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(SimpleUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public SimplePublicacaoDTO getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(SimplePublicacaoDTO publicacao) {
        this.publicacao = publicacao;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SimpleComentarioDTO getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(SimpleComentarioDTO comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioInputDTO that = (ComentarioInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(publicacao, that.publicacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, publicacao);
    }
}
