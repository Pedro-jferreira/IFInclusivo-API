package com.example.IfGoiano.IfCoders.entity;

import com.example.IfGoiano.IfCoders.entity.Enums.Curso;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "aluno")
@Data
@DiscriminatorValue("aluno")
public class AlunoEntity extends UsuarioEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "curso")
    private Curso curso;

}

