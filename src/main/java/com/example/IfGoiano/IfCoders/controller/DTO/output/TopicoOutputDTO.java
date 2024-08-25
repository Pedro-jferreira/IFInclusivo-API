package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;

import java.util.List;
import java.util.Objects;

public class TopicoOutputDTO {

    private Long id;
    private SimpleProfessorDTO professor;
    private String tema;
    private String descripcion;
    private String categoria;
    private List<SimplePublicacaoDTO> publicacoes;
    public TopicoOutputDTO() {
    }

    public TopicoOutputDTO(Long id, SimpleProfessorDTO professor, String tema, String descripcion, String categoria, List<SimplePublicacaoDTO> publicacoes) {
        this.id = id;
        this.professor = professor;
        this.tema = tema;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.publicacoes = publicacoes;
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

    public List<SimplePublicacaoDTO> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<SimplePublicacaoDTO> publicacoes) {
        this.publicacoes = publicacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicoOutputDTO that = (TopicoOutputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(professor, that.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor);
    }
}
