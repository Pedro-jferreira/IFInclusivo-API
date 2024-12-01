package com.example.IfGoiano.IfCoders.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "Tutor")
@DiscriminatorValue("TUTOR")
@Data
public class TutorEntity extends UsuarioEntity {

    @NotNull
    @Column(nullable = false)
    private String especialidade;

}
