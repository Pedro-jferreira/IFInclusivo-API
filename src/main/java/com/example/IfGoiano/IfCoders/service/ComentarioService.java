package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.PK.ComentarioId;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    public List<Comentario> findAll() {
        return repository.findAll();
    }

    public Comentario findById(ComentarioId id) {
        return (Comentario) repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Comentario save(Comentario comentario) {
        try {
            return repository.save(comentario);
        } catch (Exception e) {
            throw new DataBaseException("Error saving Comentario: " + e.getMessage());
        }
    }

    public Comentario update(Comentario comentario) {
        if (!repository.existsById(comentario.getId())) {
            throw new ResourceNotFoundException(comentario.getId());
        }
        try {
            return repository.save(comentario);
        } catch (Exception e) {
            throw new DataBaseException("Error updating Comentario: " + e.getMessage());
        }
    }

    public void deleteById(ComentarioId id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (Exception e) {
            throw new DataBaseException("Error deleting Comentario: " + e.getMessage());
        }
    }

    public List<ComentarioRepository> findByComentarioPai(Comentario comentarioPai) {
        return repository.findByComentarioPai(comentarioPai);
    }

    public Comentario addSubComentario(Comentario comentarioPai, Comentario subComentario) {
        subComentario.setComentarioPai(comentarioPai);
        try {
            return repository.save(subComentario);
        } catch (Exception e) {
            throw new DataBaseException("Error adding subComentario: " + e.getMessage());
        }
    }

    public void removeSubComentario(Comentario comentarioPai, ComentarioId subComentarioId) {
        Comentario subComentario = (Comentario) repository.findById(subComentarioId)
                .orElseThrow(() -> new ResourceNotFoundException(subComentarioId));
        comentarioPai.getComentariosFilhos().remove(subComentario);
        try {
            repository.save(comentarioPai);
        } catch (Exception e) {
            throw new DataBaseException("Error removing subComentario: " + e.getMessage());
        }
    }
}