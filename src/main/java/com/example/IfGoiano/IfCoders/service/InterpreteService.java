package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterpreteService {
    List<InterpreteOutputDTO> findAll();

    InterpreteOutputDTO findById(Long id);

    InterpreteOutputDTO save(InterpreteInputDTO interpreteInputDTO, Long idConfigAc);

    InterpreteOutputDTO update(InterpreteInputDTO interpreteOutputDTO, Long id);

    Page<LibrasOutputDTO> historicoLibrasSugeridas(Pageable pageable);

    LibrasOutputDTO analisarPalavra(RequestAnalisePalavra requestAnalisePalavra, Long idInterprete);

    void delete(Long id);
    boolean existsById(Long id);
}
