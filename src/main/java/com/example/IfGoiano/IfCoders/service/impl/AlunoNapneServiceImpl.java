package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoNapneServiceImpl {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;


    public AlunoNapneEntity createdAlunoNapne(AlunoNapneEntity alunoNapneEntity) {
       return alunoNapneRepository.save(alunoNapneEntity);
    }

    public AlunoNapneEntity findByIdAlunoNapne(Long id){
        return alunoNapneRepository.findById(id).get();
    }

}
