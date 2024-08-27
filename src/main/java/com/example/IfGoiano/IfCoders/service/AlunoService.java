package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;

import java.util.List;

public interface AlunoService {
    List<AlunoOutputDTO> findAll();

    AlunoOutputDTO findById(Long id);

    AlunoOutputDTO save(AlunoInputDTO aluno);

    AlunoOutputDTO update(Long id, AlunoInputDTO alunoDetails);

    void delete(Long id);
    boolean existsById(Long id);
}
