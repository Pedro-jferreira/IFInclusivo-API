package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;

import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl {

    @Autowired
    ProfessorRepository professorRepository;



    public ProfessorEntity save(ProfessorEntity professorEntity){
        return   professorRepository.save(professorEntity);
    }

    public List<ProfessorEntity> findAll(){
        return professorRepository.findAll();
    }

    public ProfessorEntity findById(Long id){
        return professorRepository.findById(id).get();
    }

    public void delete(Long id){
        professorRepository.deleteById(id);
    }

    public  ProfessorEntity update(ProfessorEntity professor){
        var profe = professorRepository.findById(professor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        profe.setId(professor.getId());//
        profe.setNome(professor.getNome());//
        profe.setBiografia(professor.getBiografia());//
        profe.setComentarios(professor.getComentarios());//
        profe.setFormacao(professor.getFormacao());//
        profe.setMatricula(professor.getMatricula());//
        profe.setLogin(professor.getLogin());//
        profe.setSenha(professor.getSenha());

        return  professorRepository.save(profe);

    }
}