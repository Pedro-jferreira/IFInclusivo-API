package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Libras;;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrasService {

    @Autowired
    LibrasRepository librasRepository;


    public Libras createLibras(Libras libras) {
        return librasRepository.save(libras);
    }

    public Libras findLibrasById(Long id) {
        var libras = librasRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Libras not found"));

        return libras;
    }


    public Libras updateLibras(Libras libras) {
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

    public List<Libras> findAllLibras() {
        return librasRepository.findAll();
    }

    public void deleteLibras(Long id) {
        librasRepository.deleteById(id);
    }

}