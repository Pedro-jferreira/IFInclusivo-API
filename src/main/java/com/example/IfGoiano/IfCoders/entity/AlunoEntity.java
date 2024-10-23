package com.example.IfGoiano.IfCoders.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class AlunoEntity extends UsuarioEntity {
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

}

