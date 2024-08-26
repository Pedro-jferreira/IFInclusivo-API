package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class InterpreteInputDTO extends TutorInputDTO{
    private Double salary;


    public InterpreteInputDTO() {  super();   }


    public InterpreteInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity,  String especialidade, Double salary) {
        super(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity,  especialidade);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


}
