package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ComentarioOutputDTO {

    private Long id;
    private SimpleUsuarioDTO usuario;
    private SimplePublicacaoDTO publicacaoEntity;
    private String content;
    private LocalDateTime dataCriacao;
    private SimpleComentarioDTO comentarioPai;
    private List<SimpleComentarioDTO> comentariosFilhos;
    private List<SimpleUsuarioDTO> usefulBy;

    public ComentarioOutputDTO() {
    }

    public ComentarioOutputDTO(Long id, SimpleUsuarioDTO usuario, SimplePublicacaoDTO publicacaoEntity, String content, LocalDateTime dataCriacao, SimpleComentarioDTO comentarioPai, List<SimpleComentarioDTO> comentariosFilhos, List<SimpleUsuarioDTO> usefulBy) {
        this.id = id;
        this.usuario = usuario;
        this.publicacaoEntity = publicacaoEntity;
        this.content = content;
        this.dataCriacao = dataCriacao;
        this.comentarioPai = comentarioPai;
        this.comentariosFilhos = comentariosFilhos;
        this.usefulBy = usefulBy;
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

    public SimplePublicacaoDTO getPublicacaoEntity() {
        return publicacaoEntity;
    }

    public void setPublicacaoEntity(SimplePublicacaoDTO publicacaoEntity) {
        this.publicacaoEntity = publicacaoEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public SimpleComentarioDTO getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(SimpleComentarioDTO comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public List<SimpleComentarioDTO> getComentariosFilhos() {
        return comentariosFilhos;
    }

    public void setComentariosFilhos(List<SimpleComentarioDTO> comentariosFilhos) {
        this.comentariosFilhos = comentariosFilhos;
    }

    public List<SimpleUsuarioDTO> getUsefulBy() {
        return usefulBy;
    }

    public void setUsefulBy(List<SimpleUsuarioDTO> usefulBy) {
        this.usefulBy = usefulBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioOutputDTO that = (ComentarioOutputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(publicacaoEntity, that.publicacaoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, publicacaoEntity);
    }
}
