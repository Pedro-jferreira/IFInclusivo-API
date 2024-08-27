package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;

import java.util.List;

public interface CursoService {
    List<CursoOutputDTO> findAll();

    CursoOutputDTO findById(Long id);

    CursoOutputDTO save(CursoInputDTO cursoEntity);

    CursoOutputDTO update(Long id, CursoInputDTO cursoEntityDetails);

    void delete(Long id);
}
