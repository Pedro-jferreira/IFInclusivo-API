package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class TutorSimplificadoDTO extends UsuarioSimplificadoDTO{
    private String especialidade;


    public TutorSimplificadoDTO() {    }

    public TutorSimplificadoDTO(String nome, Long matricula, String especialidade) {
        super(nome, matricula);
        this.especialidade = especialidade;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TutorSimplificadoDTO that = (TutorSimplificadoDTO) o;
        return Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidade);
    }
}
