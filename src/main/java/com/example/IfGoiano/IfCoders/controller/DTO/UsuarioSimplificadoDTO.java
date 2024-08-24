package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class UsuarioSimplificadoDTO {
    private String nome;
    private Long matricula;


    public UsuarioSimplificadoDTO() {  }

    public UsuarioSimplificadoDTO(String nome, Long matricula) {
        this.nome = nome;
        this.matricula = matricula;
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
        UsuarioSimplificadoDTO that = (UsuarioSimplificadoDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, matricula);
    }
}
