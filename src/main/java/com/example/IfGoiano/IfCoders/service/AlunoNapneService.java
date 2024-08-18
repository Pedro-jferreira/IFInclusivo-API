package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.AlunoNapne;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoNapneService {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;


    public AlunoNapne createdAlunoNapne(AlunoNapne alunoNapneEntity) {
       return alunoNapneRepository.save(alunoNapneEntity);
    }

    public AlunoNapne findByIdAlunoNapne(Long id){
        return alunoNapneRepository.findById(id).get();
    }

}
