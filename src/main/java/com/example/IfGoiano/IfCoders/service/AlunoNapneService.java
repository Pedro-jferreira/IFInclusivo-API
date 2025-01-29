package com.example.IfGoiano.IfCoders.service;


import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;

import java.util.List;

public interface AlunoNapneService {
    List<AlunoNapneOutputDTO> findAll();

    AlunoNapneOutputDTO findById(Long id);

    AlunoNapneOutputDTO save(AlunoNapneInputDTO alunoNapne, Long idCurso, Long idConfigAc);

    AlunoNapneOutputDTO update(AlunoNapneInputDTO alunoNapne, Long id);

    void delete(Long id);
    boolean existsById(Long id);
}
