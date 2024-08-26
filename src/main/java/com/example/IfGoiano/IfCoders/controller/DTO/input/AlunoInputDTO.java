package com.example.IfGoiano.IfCoders.controller.DTO.input;



public class AlunoInputDTO extends UsuarioInputDTO{
    private CursoInputDTO cursoDTO;


    public AlunoInputDTO() {   super();  }


    public AlunoInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcblInputDTO configAcessibilidadeEntity, CursoInputDTO cursoDTO) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
        this.cursoDTO = cursoDTO;
    }

    public CursoInputDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoInputDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }

}
