package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class TutorInputDTO extends UsuarioInputDTO {
    private String especialidade;


    public TutorInputDTO() {
        super();
    }

    public TutorInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity,  String especialidade) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


}
