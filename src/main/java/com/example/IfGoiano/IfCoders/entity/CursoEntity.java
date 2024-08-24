package com.example.IfGoiano.IfCoders.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cursoEntity",cascade = CascadeType.ALL)
    private List<AlunoEntity> alunos;

    public CursoEntity() {

    }

    public Long getId() {
        return id;
    }

    public CursoEntity(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AlunoEntity> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoEntity> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEntity cursoEntity = (CursoEntity) o;
        return Objects.equals(id, cursoEntity.id) && Objects.equals(nome, cursoEntity.nome) && Objects.equals(alunos, cursoEntity.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, alunos);
    }
}
