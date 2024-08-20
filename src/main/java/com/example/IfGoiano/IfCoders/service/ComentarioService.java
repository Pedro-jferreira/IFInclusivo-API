package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.DTO.ComentarioDTO;
import com.example.IfGoiano.IfCoders.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

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
            Optional<Comentario> comentario = repository.findById(id);
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
            Optional<Comentario> comentario = repository.findById(id);
            if (comentario.isPresent()) {
                Comentario comentario1 = comentario.get();
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
            Optional<Comentario> comentario = repository.findById(id);
            comentario.ifPresent(value -> repository.delete(value));

        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the comment"+ e);
        }
    }
    public Optional<Comentario> findByUsuarioAndPublicacao(Usuario usuario, Publicacao publicacao){


        return repository.findById(1L);
    }

}
