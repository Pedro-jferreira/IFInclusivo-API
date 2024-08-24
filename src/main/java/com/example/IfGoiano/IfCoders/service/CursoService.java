package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.entity.CursoEntity;

import java.util.List;

public interface CursoService {
    List<CursoEntity> findAll();

    CursoEntity findById(Long id);

    CursoEntity save(CursoEntity curso);

    CursoEntity update(Long id, CursoEntity cursoDetails);

    void delete(Long id);
}
