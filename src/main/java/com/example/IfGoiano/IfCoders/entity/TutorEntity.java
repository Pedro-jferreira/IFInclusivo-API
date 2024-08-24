package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name= "Tutor")
public class TutorEntity extends UsuarioEntity {

    @NotNull    @Column(nullable = false)
    private String especialidade;


    public TutorEntity() {    }

    public TutorEntity(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, String especialidade) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
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
        TutorEntity tutor = (TutorEntity) o;
        return Objects.equals(especialidade, tutor.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidade);
    }
}
