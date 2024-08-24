package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import java.util.List;
import java.util.Objects;

public class TutorOutputDTO extends UsuarioOutputDTO{
    private String especialidade;


    public TutorOutputDTO() {    }

    public TutorOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia,
                          ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios,
                          List<PublicacaoEntity> publicacaoEntities, List<ConfigAcessibilidadeEntity> config, String especialidade) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
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
        TutorOutputDTO that = (TutorOutputDTO) o;
        return Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidade);
    }
}
