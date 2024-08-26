package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;

public class SimpleAlunoDTO extends SimpleUsuarioDTO {

    private CursoInputDTO cursoDTO;

    public SimpleAlunoDTO() {  super();}

    public SimpleAlunoDTO(Long id, String nome, Long matricula,  CursoInputDTO cursoDTO) {
        super(id, nome, matricula);
        this.cursoDTO = cursoDTO;
    }

    public CursoInputDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoInputDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }
}