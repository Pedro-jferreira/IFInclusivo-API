package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class SimpleUsuarioDTO {
    private Long id;
    private String nome;
    private Long matricula;


    public SimpleUsuarioDTO() {  }

    public SimpleUsuarioDTO(Long id, String nome, Long matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUsuarioDTO that = (SimpleUsuarioDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, matricula);
    }
}
