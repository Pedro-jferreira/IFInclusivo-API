package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Professor;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;



    public Professor createProfessor(Professor professorEntity){
        return   professorRepository.save(professorEntity);
    }

    public List<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }

    public Professor getByIdProfessor(Long id){
        return professorRepository.findById(id).get();
    }

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

    public  Professor updateProfessor(Professor professor){
        var profe = professorRepository.findById(professor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        profe.setId(professor.getId());//
        profe.setNome(professor.getNome());//
        profe.setBiografia(professor.getBiografia());//
        profe.setComentarios(professor.getComentarios());//
        profe.setLikes(professor.getLikes());//
        profe.setFormacao(professor.getFormacao());//
        profe.setMatricula(professor.getMatricula());//
        profe.setLogin(professor.getLogin());//
        profe.setPublicacaos(professor.getPublicacaos());//
        profe.setResolveuProblemas(professor.getResolveuProblemas());//
        profe.setSenha(professor.getSenha());

        return  professorRepository.save(profe);

    }
}