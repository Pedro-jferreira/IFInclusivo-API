package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import java.util.List;
import java.util.Objects;

public class ProfessorOutputDTO extends UsuarioOutputDTO {
    private String formacao;
    private List<SimpleTopicoDTO> topicos;


    public ProfessorOutputDTO() {super();    }


    public ProfessorOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<SimpleComentarioDTO> comentarios, List<SimplePublicacaoDTO> publicacaoEntities, List<ConfigAcessibilidadeEntity> config,  String formacao, List<SimpleTopicoDTO> topicos) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
        this.formacao = formacao;
        this.topicos = topicos;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public List<SimpleTopicoDTO> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<SimpleTopicoDTO> topicos) {
        this.topicos = topicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProfessorOutputDTO that = (ProfessorOutputDTO) o;
        return Objects.equals(formacao, that.formacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formacao);
    }
}
