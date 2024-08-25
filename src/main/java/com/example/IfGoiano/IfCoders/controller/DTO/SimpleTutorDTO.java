package com.example.IfGoiano.IfCoders.controller.DTO;

public class SimpleTutorDTO extends SimpleUsuarioDTO {
    private String especialidade;


    public SimpleTutorDTO() {    }

    public SimpleTutorDTO(Long id, String nome, Long matricula, String especialidade) {
        super(id, nome, matricula);
        this.especialidade = especialidade;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
