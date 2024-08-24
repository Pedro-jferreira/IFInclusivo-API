package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
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

    public List<ComentarioDTO> findAll() {
        try {
            return repository.findAll().stream().map(mapper::comentarioToComentarioDTO).collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while fetching all comments"+ e);
        }
    }

    public ComentarioDTO findById(Long id) {
        try {
            Optional<ComentarioEntity> comentario = repository.findById(id);
            if (comentario.isPresent()) return mapper.comentarioToComentarioDTO(comentario.get());
            else throw  new ResourceNotFoundException(id);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while fetching comment : " + e);
        }
    }

    @Transactional
    public ComentarioDTO save(ComentarioDTO comentario) {
        try {
            if (comentario.getComentarioPaiDTO() == null) {
                comentario.setComentarioPaiDTO(comentario);
            }
            return mapper.comentarioToComentarioDTO(repository.save(mapper.comentarioDTOToComentario(comentario)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while saving the comment"+ e);
        }
    }

    @Transactional
    public ComentarioDTO update(Long id, ComentarioDTO comentarioDetails) {
        try {
            Optional<ComentarioEntity> comentario = repository.findById(id);
            if (comentario.isPresent()) {
                ComentarioEntity comentario1 = comentario.get();
                mapper.updateComentarioFromDTO(comentarioDetails,comentario1);
                return mapper.comentarioToComentarioDTO(repository.save(comentario1));
            }else throw  new ResourceNotFoundException(id);

        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the comment"+ e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            Optional<ComentarioEntity> comentario = repository.findById(id);
            comentario.ifPresent(value -> repository.delete(value));

        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the comment"+ e);
        }
    }
    public Optional<ComentarioEntity> findByUsuarioAndPublicacao(UsuarioEntity usuario, PublicacaoEntity publicacaoEntity){


        return repository.findById(1L);
    }

}
