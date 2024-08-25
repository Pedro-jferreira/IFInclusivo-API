package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
public class LibrasServiceImpl {

    @Autowired
    LibrasRepository librasRepository;


    public LibrasEntity save(LibrasEntity libras) {
        return librasRepository.save(libras);
    }

    public LibrasEntity findById(Long id) {
        var libras = librasRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Libras not found"));

        return libras;
    }


    public LibrasEntity update(LibrasEntity libras) {
        var libraAux = librasRepository.findById(libras.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Libras not found"));

        libraAux.setId(libras.getId());
        libraAux.setDescricao(libras.getDescricao());
        libraAux.setFoto(libras.getFoto());
        libraAux.setPalavra(libras.getPalavra());
        libraAux.setStatus(libras.getStatus());
        libraAux.setUrl(libras.getUrl().toString());
        libraAux.setInterpreteAnalise(libras.getInterpreteAnalise());
        libraAux.setJustificativa(libras.getJustificativa());
        libraAux.setVideo(libras.getVideo());
        return librasRepository.save(libraAux);
    }

    public List<LibrasEntity> findAll() {
        return librasRepository.findAll();
    }


    public void delete(Long id) {
        librasRepository.deleteById(id);
    }

}