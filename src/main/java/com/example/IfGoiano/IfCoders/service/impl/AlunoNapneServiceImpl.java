package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoNapneServiceImpl {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;


    public AlunoNapneEntity save(AlunoNapneEntity alunoNapneEntity) {
        return alunoNapneRepository.save(alunoNapneEntity);
    }

    public AlunoNapneEntity findById(Long id){
        return alunoNapneRepository.findById(id).get();
    }

    public List<AlunoNapneEntity> findAll(){
        return alunoNapneRepository.findAll();
    }

    public AlunoNapneEntity updateAlunoNapne(AlunoNapneEntity alunoNapneEntity){
        var aluno = alunoNapneRepository.findById(alunoNapneEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AlunoNapne not found"));

        aluno.setAcompanhamento(alunoNapneEntity.getAcompanhamento());
        aluno.setId(alunoNapneEntity.getId());
        aluno.setCondicao(alunoNapneEntity.getCondicao());
        aluno.setNecessidadeEscolar(alunoNapneEntity.getNecessidadeEscolar());
        aluno.setNecessidadeEspecial(alunoNapneEntity.getNecessidadeEspecial());

        return alunoNapneRepository.save(aluno);

    }

    public void delete(Long id){
        var aluno = alunoNapneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlunoNapne not found"));

        alunoNapneRepository.delete(aluno);
    }


}
