package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.LibrasService;
import com.example.IfGoiano.IfCoders.utils.UsuarioFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LibrasServiceImpl implements LibrasService {

    @Autowired
    LibrasRepository repository;
    @Autowired
    LibrasMapper mapper;

    @Autowired
    private UsuarioFinder usuarioFinder;



    public LibrasOutputDTO findById(Long id) {
        var libras = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Libras not found"));
        return mapper.toLibrasOutputDTO(libras);
    }


    public LibrasOutputDTO update(LibrasInputDTO libras, Long id) {
        var libraAux = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Libras not found"));
        mapper.updateLibrasEntityFromDTO(libras,libraAux);
        return mapper.toLibrasOutputDTO(repository.save(libraAux));
    }

    public List<LibrasOutputDTO> findAll() {
        return repository.findAll().stream().map(mapper::toLibrasOutputDTO).collect(Collectors.toList());
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public LibrasOutputDTO save(LibrasInputDTO libras){

        LibrasEntity librasEntity = mapper.toLibrasEntity(libras);
        this.repository.save(librasEntity);
        return mapper.toLibrasOutputDTO(librasEntity);
    }

    public LibrasOutputDTO sugereLibras(LibrasInputDTO libras, Long idUser){
        var usuario =  usuarioFinder.findUsuarioById(idUser);

        LibrasEntity librasEntity = mapper.toLibrasEntity(libras);
        librasEntity.setSugeriu(usuario);
        return findById(repository.save(librasEntity).getId());
    }

}