package com.example.IfGoiano.IfCoders.service;


import java.util.List;

public interface AlunoNapneService {
    List<AlunoNapneDTO> findAll();

    OutPutAlunoNapne findById(Long id);

    InputAlunoNapne save( InputAlunoNapne);

    OutPutAlunoNapne update(InputAlunoNapne);

    void delete(Long id);
}
