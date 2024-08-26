package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;


import java.util.List;

public class AlunoOutputDTO extends UsuarioOutputDTO {
    private CursoInputDTO cursoDTO;


    public AlunoOutputDTO() {
        super();
    }

    public AlunoOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcblInputDTO configAcessibilidadeEntity, List<SimpleComentarioDTO> comentarios, List<SimplePublicacaoDTO> publicacaoEntities,  CursoInputDTO cursoDTO) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities);
        this.cursoDTO = cursoDTO;
    }


    public CursoInputDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoInputDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }

}
