package com.example.IfGoiano.IfCoders.controller.DTO;

public class SimpleInterpreteDTO extends SimpleTutorDTO {
    private Double salary;


    public SimpleInterpreteDTO() {    super(); }


    public SimpleInterpreteDTO(Long id, String nome, Long matricula, String especialidade, Double salary) {
        super(id, nome, matricula,  especialidade);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
