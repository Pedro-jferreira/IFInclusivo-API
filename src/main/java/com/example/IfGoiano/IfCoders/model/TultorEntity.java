package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class TutorEntity extends Usuario{

    private String especialidade;



    public TutorEntity() {

    }

    public TutorEntity(Long id,String nome,String especialidade  ,String login, String senha, Long matricula, String biografia) {
        super(id,nome,login,senha,matricula,biografia);
        this.especialidade = especialidade;
    }



    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
