package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleCursoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.List;
import java.util.Objects;

public class AlunoOutputDTO extends UsuarioOutputDTO {
    private SimpleCursoDTO cursoDTO;


    public AlunoOutputDTO() {
    }

    public AlunoOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<SimpleComentarioDTO> comentarios, List<SimplePublicacaoDTO> publicacaoEntities, List<ConfigAcessibilidadeEntity> config, SimpleCursoDTO cursoDTO) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
        this.cursoDTO = cursoDTO;
    }

    public SimpleCursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(SimpleCursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }

}
