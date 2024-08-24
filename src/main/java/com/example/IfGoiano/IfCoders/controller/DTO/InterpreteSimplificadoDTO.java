package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class InterpreteSimplificadoDTO extends TutorSimplificadoDTO{
    private Double salary;


    public InterpreteSimplificadoDTO() {    }

    public InterpreteSimplificadoDTO(String nome, Long matricula, String especialidade, Double salary) {
        super(nome, matricula, especialidade);
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
        InterpreteSimplificadoDTO that = (InterpreteSimplificadoDTO) o;
        return Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }
}
