package com.example.IfGoiano.IfCoders.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "professor")
@Data
@DiscriminatorValue("professor")
public class ProfessorEntity extends UsuarioEntity {

    private String formacao;


    public ProfessorEntity() {
        super();
    }
}
