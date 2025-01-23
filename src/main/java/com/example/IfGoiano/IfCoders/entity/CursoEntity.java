package com.example.IfGoiano.IfCoders.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Data
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<AlunoEntity> alunos;
}
