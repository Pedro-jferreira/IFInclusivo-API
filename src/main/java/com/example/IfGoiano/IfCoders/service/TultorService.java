package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.TultorEntity;
import com.example.IfGoiano.IfCoders.repository.TultorRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TultorService {

    @Autowired
    TultorRepository tultorRepository;


    public TultorEntity createdTultor(TultorEntity tultorEntity) {
        return tultorRepository.save(tultorEntity);
    }

    public TultorEntity findByIdTultor(Long id){
        var tultor = tultorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));

        return tultor;
    }

    public List<TultorEntity> findAllTultor(){
        return tultorRepository.findAll();
    }

    public TultorEntity updateTultor(TultorEntity tultorEntity) {
        var tultor = tultorRepository.findById(tultorEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));
        tultor.setId(tultorEntity.getId());
        tultor.setEspecialidade(tultorEntity.getEspecialidade());
        tultor.setNome(tultorEntity.getNome());
        tultor.setBiografia(tultorEntity.getBiografia());
        tultor.setComentarios(tultorEntity.getComentarios());
        tultor.setLikes(tultorEntity.getLikes());
        tultor.setMatricula(tultorEntity.getMatricula());
        tultor.setPublicacaos(tultorEntity.getPublicacaos());
        tultor.setResolveuProblemas(tultorEntity.getResolveuProblemas());
        tultor.setLogin(tultorEntity.getLogin());
        tultor.setSenha(tultorEntity.getSenha());
        return tultorRepository.save(tultor);
    }

    public void deleteTultor(Long id) {
        tultorRepository.deleteById(id);

    }
}
