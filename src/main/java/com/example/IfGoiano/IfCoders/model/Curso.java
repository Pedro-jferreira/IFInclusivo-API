package com.example.IfGoiano.IfCoders.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    public Curso() {

    }

    public Curso(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id) && Objects.equals(nome, curso.nome) && Objects.equals(alunos, curso.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, alunos);
    }
}
