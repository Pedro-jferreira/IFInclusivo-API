package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AlunoNapneEntity> findAllAlunoNapne(){
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

    public void deleteAlunoNapne(Long id){
        var aluno = alunoNapneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlunoNapne not found"));

        alunoNapneRepository.delete(aluno);
    }

}
