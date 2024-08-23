package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Interprete;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpreteService {

    @Autowired
    InterpreteRepository interpreteRepository;

    public Interprete createdInterprete(Interprete interpreteEntity) {
        return interpreteRepository.save(interpreteEntity);
    }

    public Interprete findByIdInterprete(Long id) {
        var interprete = interpreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interprete;
    }

    public Interprete updateInterprete(Interprete interpreteEntity) {
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

    public List<Interprete> findAllInterpretes() {
        return interpreteRepository.findAll();
    }

    public void deleteInterprete(Long id) {
        interpreteRepository.deleteById(id);
    }
}
