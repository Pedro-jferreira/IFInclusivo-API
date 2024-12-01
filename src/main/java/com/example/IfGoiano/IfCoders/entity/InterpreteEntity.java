package com.example.IfGoiano.IfCoders.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "interprete")
@Data
@DiscriminatorValue("INTERPRETE")
public class InterpreteEntity extends TutorEntity {

    @NotNull    @Column(nullable = false)
    private Double salary;

    @ManyToMany
    @JoinTable(name = "interprete_libras", joinColumns = @JoinColumn(name = "interprete_id"), inverseJoinColumns = @JoinColumn(name= "libras_id"))
    private List<LibrasEntity> libras = new ArrayList<>();


}
