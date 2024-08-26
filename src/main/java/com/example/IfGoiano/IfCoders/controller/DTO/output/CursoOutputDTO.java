package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;



import java.util.List;

public class CursoOutputDTO {
    private Long id;
    private String nome;
    private List<SimpleAlunoDTO> alunos;

    public CursoOutputDTO() {}
    public CursoOutputDTO(Long id, String nome, List<SimpleAlunoDTO> alunos) {
        this.id = id;
        this.nome = nome;
        this.alunos = alunos;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<SimpleAlunoDTO> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<SimpleAlunoDTO> alunos) {
        this.alunos = alunos;
    }
}
