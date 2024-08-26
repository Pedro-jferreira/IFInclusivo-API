package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;


import java.util.Objects;

public class ComentarioInputDTO {

    private Long id;
    private String content;
    private SimpleComentarioDTO comentarioPai;

    public ComentarioInputDTO() {
    }

    public ComentarioInputDTO(Long id, String content, SimpleComentarioDTO comentarioPai) {
        this.id = id;

        this.content = content;
        this.comentarioPai = comentarioPai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, that.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
