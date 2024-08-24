package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.entity.AlunoEntity;

import java.util.List;

public interface AlunoService {
    List<AlunoEntity> findAll();

    AlunoEntity findById(Long id);

    AlunoEntity save(AlunoEntity aluno);

    AlunoEntity update(Long id, AlunoEntity alunoDetails);

    void delete(Long id);
}
