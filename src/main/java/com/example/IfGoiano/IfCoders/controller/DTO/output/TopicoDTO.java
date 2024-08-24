package com.example.IfGoiano.IfCoders.controller.DTO.output;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class TopicoDTO extends RepresentationModel<TopicoDTO> {
    private Long id;
    private String tema;
    private String descripcion;
    private String categoria;

    public TopicoDTO() {
    }

    public TopicoDTO(Long id, String tema, String descripcion, String categoria) {
        this.id = id;
        this.tema = tema;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TopicoDTO topicoDTO = (TopicoDTO) o;
        return Objects.equals(id, topicoDTO.id) && Objects.equals(tema, topicoDTO.tema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, tema);
    }
}
