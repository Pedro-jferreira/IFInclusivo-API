package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;

import java.util.List;

public interface InterpreteService {
    List<InterpreteOutputDTO> findAll();

    InterpreteOutputDTO findById(Long id);

    InterpreteOutputDTO save(InterpreteInputDTO interpreteOutputDTO);

    InterpreteOutputDTO update(InterpreteInputDTO interpreteOutputDTO, Long id);

    void delete(Long id);
}
