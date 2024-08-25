package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;

import java.util.Objects;

public class TopicoInputDTO {
    private Long id;
    private SimpleProfessorDTO professor;
    private String tema;
    private String descripcion;
    private String categoria;

    public TopicoInputDTO() {
    }

    public TopicoInputDTO(Long id, SimpleProfessorDTO professor, String tema, String descripcion, String categoria) {
        this.id = id;
        this.professor = professor;
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

    public SimpleProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(SimpleProfessorDTO professor) {
        this.professor = professor;
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
        TopicoInputDTO that = (TopicoInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(professor, that.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor);
    }
}
