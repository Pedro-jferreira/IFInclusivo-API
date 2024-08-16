package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.InterpreteEntity;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpreteService {

    @Autowired
    InterpreteRepository interpreteRepository;

    public InterpreteEntity createdInterprete(InterpreteEntity interpreteEntity) {
        return interpreteRepository.save(interpreteEntity);
    }

    public InterpreteEntity findByIdInterprete(Long id) {
        var interprete = interpreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interprete;
    }

    public InterpreteEntity updateInterprete(InterpreteEntity interpreteEntity) {
        var interprete = interpreteRepository.findById(interpreteEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        interprete.setId(interpreteEntity.getId());
        interprete.setLibras(interpreteEntity.getLibras());
        interprete.setBiografia(interpreteEntity.getBiografia());
        interprete.setSalary(interpreteEntity.getSalary());
        interprete.setEspecialidade(interpreteEntity.getEspecialidade());
        interprete.setComentarios(interpreteEntity.getComentarios());
        interprete.setLikes(interpreteEntity.getLikes());
        interprete.setLogin(interpreteEntity.getLogin());
        interprete.setMatricula(interpreteEntity.getMatricula());
        interprete.setNome(interpreteEntity.getNome());
        interprete.setPublicacaos(interpreteEntity.getPublicacaos());
        interprete.setResolveuProblemas(interpreteEntity.getResolveuProblemas());
        interprete.setSenha(interpreteEntity.getSenha());
        return interpreteRepository.save(interprete);
    }

    public List<InterpreteEntity> findAllInterpretes() {
        return interpreteRepository.findAll();
    }

    public void deleteInterprete(Long id) {
        interpreteRepository.deleteById(id);
    }
}

