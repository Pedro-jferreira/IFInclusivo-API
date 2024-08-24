package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.Objects;

public class InterpreteInputDTO extends TutorInputDTO{
    private Double salary;


    public InterpreteInputDTO() {    }

    public InterpreteInputDTO(String nome, String login, String senha, Long matricula, String biografia,
                              ConfigAcessibilidadeEntity configAcessibilidadeEntity, String especialidade, Double salary) {
        super(nome, login, senha, matricula, biografia, configAcessibilidadeEntity, especialidade);
        this.salary = salary;
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterpreteInputDTO that = (InterpreteInputDTO) o;
        return Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }
}
