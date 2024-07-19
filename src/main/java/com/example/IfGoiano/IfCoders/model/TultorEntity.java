package com.example.IfGoiano.IfCoders.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "Tultor")
public class TultorEntity extends Usuario{

    private String especialidade;



    public TultorEntity() {

    }

    public TultorEntity(Long id, String nome, String especialidade  , String login, String senha, Long matricula, String biografia) {
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
