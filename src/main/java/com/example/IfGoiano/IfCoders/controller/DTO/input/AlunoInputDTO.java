package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleCursoDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

public class AlunoInputDTO extends UsuarioInputDTO{
    private SimpleCursoDTO cursoDTO;


    public AlunoInputDTO() {   super();  }


    public AlunoInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, SimpleCursoDTO cursoDTO) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
        this.cursoDTO = cursoDTO;
    }

    public SimpleCursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(SimpleCursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }

}
