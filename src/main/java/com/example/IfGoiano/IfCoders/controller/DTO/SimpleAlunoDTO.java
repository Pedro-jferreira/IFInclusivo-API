package com.example.IfGoiano.IfCoders.controller.DTO;

public class SimpleAlunoDTO extends SimpleUsuarioDTO {

    private SimpleCursoDTO cursoDTO;

    public SimpleAlunoDTO() { }

    public SimpleAlunoDTO(Long id, String nome, Long matricula, SimpleCursoDTO cursoDTO) {
        super(id, nome, matricula);
        this.cursoDTO = cursoDTO;
    }

    public SimpleCursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(SimpleCursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }
}