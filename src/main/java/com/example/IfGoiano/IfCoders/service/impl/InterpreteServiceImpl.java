package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpreteServiceImpl {

    @Autowired
    InterpreteRepository interpreteRepository;

    public InterpreteEntity save(InterpreteEntity interpreteEntity) {
        return interpreteRepository.save(interpreteEntity);
    }

    public InterpreteEntity findById(Long id) {
        var interprete = interpreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interprete;
    }

    public InterpreteEntity update(InterpreteEntity interpreteEntity) {
        var interprete = interpreteRepository.findById(interpreteEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        interprete.setId(interpreteEntity.getId());
        interprete.setLibras(interpreteEntity.getLibras());
        interprete.setBiografia(interpreteEntity.getBiografia());
        interprete.setSalary(interpreteEntity.getSalary());
        interprete.setEspecialidade(interpreteEntity.getEspecialidade());
        interprete.setComentarios(interpreteEntity.getComentarios());
        interprete.setLogin(interpreteEntity.getLogin());
        interprete.setMatricula(interpreteEntity.getMatricula());
        interprete.setNome(interpreteEntity.getNome());
        interprete.setSenha(interpreteEntity.getSenha());
        return interpreteRepository.save(interprete);
    }

    public List<InterpreteEntity> findAll() {
        return interpreteRepository.findAll();
    }

    public void delete(Long id) {
        interpreteRepository.deleteById(id);
    }
}
