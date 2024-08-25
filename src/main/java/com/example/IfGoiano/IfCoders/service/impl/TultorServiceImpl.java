package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TultorServiceImpl {

    @Autowired
    private TutorRepository tultorRepository;


    public TutorEntity save(TutorEntity tultorEntity) {
        return tultorRepository.save(tultorEntity);
    }

    public TutorEntity findById(Long id){
        var tultor = tultorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));

        return tultor;
    }

    public List<TutorEntity> findAll(){
        return tultorRepository.findAll();
    }

    public TutorEntity update(TutorEntity tultorEntity) {
        var tultor = tultorRepository.findById(tultorEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));
        tultor.setId(tultorEntity.getId());
        tultor.setEspecialidade(tultorEntity.getEspecialidade());
        tultor.setNome(tultorEntity.getNome());
        tultor.setBiografia(tultorEntity.getBiografia());
        tultor.setComentarios(tultorEntity.getComentarios());
        tultor.setMatricula(tultorEntity.getMatricula());
        tultor.setPublicacaos(tultorEntity.getPublicacaos());
        tultor.setLogin(tultorEntity.getLogin());
        tultor.setSenha(tultorEntity.getSenha());
        return tultorRepository.save(tultor);
    }

    public void delete(Long id) {
        tultorRepository.deleteById(id);

    }
}
