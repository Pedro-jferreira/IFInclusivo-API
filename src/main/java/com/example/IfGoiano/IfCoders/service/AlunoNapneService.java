package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoNapneService {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;


    public AlunoNapneEntity createdAlunoNapne(AlunoNapneEntity alunoNapneEntity) {
       return alunoNapneRepository.save(alunoNapneEntity);
    }

    public AlunoNapneEntity findByIdAlunoNapne(Long id){
        return alunoNapneRepository.findById(id).get();
    }

}
