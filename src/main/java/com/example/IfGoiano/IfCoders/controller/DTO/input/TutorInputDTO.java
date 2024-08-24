package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class TutorInputDTO extends UsuarioInputDTO{
    private String especialidade;


    public TutorInputDTO() {    }

    public TutorInputDTO(String nome, String login, String senha, Long matricula, String biografia,
                         ConfigAcessibilidadeEntity configAcessibilidadeEntity, String especialidade) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
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
        TutorInputDTO that = (TutorInputDTO) o;
        return Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidade);
    }
}
