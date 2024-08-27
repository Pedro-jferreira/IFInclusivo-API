package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;

import java.util.List;

public interface ProfessorService {
    List<ProfessorOutputDTO> findAll();

    ProfessorOutputDTO findById(Long id);

    ProfessorOutputDTO save(ProfessorInputDTO professorInputDTO);

    ProfessorOutputDTO update(ProfessorInputDTO professorInputDTO, Long id);

    void delete(Long id);
    boolean existsById(Long id);
}
