package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl {

    @Autowired
    private ComentarioRepository repository;
    @Autowired
    private ComentarioMapper mapper;


    @Transactional
    public ComentarioOutputDTO save(ComentarioInputDTO comentario) {
        return mapper.toComentarioOutputDTO(repository.save(mapper.toComentarioEntity(comentario)));
    }

    @Transactional
    public ComentarioOutputDTO update(Long id, ComentarioInputDTO comentarioDetails) {
        Optional<ComentarioEntity> comentario = repository.findById(id);
        if (comentario.isPresent()) {
            ComentarioEntity comentario1 = comentario.get();
            mapper.updateComentarioEntityFromDTO(comentarioDetails,comentario1);
            return mapper.toComentarioOutputDTO(repository.save(comentario1));
        }else throw  new ResourceNotFoundException(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(mapper.toComentarioEntity(findById(id)));
    }

    @Transactional
    public ComentarioOutputDTO findById(Long id) {
        Optional<ComentarioEntity> comentario = repository.findById(id);
        if (comentario.isPresent()) return mapper.toComentarioOutputDTO(comentario.get());
        else throw  new ResourceNotFoundException(id);
    }

    @Transactional
    public List<ComentarioOutputDTO> findAll() {
        try {
            return repository.findAll().stream().map(mapper::toComentarioOutputDTO).collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while fetching all comments"+ e);
        }
    }

}