package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Like;
import com.example.IfGoiano.IfCoders.model.PK.LikeId;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.LikeRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public List<Like> findAll() {
        try {
            return likeRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all likes"+ e);
        }
    }

    public Like findById(LikeId id) {
        try {
            Optional<Like> like = likeRepository.findById(id);
            return like.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching like: " + e);
        }
    }

    @Transactional
    public Like save(Like like) {
        try {
            return likeRepository.save(like);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving the like"+ e);
        }
    }

    @Transactional
    public Like update(LikeId id, Like likeDetails) {
        try {
            Like like = findById(id);
            updateLikeDetails(like,likeDetails);
            return likeRepository.save(like);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the like"+ e);
        }
    }

    @Transactional
    public void delete(LikeId id) {
        try {
            Like like = findById(id);
            likeRepository.delete(like);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the like"+ e);
        }
    }

    public Optional<Like> findByUsuarioAndPublicacao(Usuario usuario, Publicacao publicacao) {
        LikeId likeId = new LikeId();
        likeId.setUsuario(usuario);
        likeId.setPublicacao(publicacao);
        return likeRepository.findById(likeId);
    }

    private void updateLikeDetails(Like like, Like likeDetails) {
        like.setPublicacao(likeDetails.getPublicacao());
        like.setUsuario(likeDetails.getUsuario());


    }
}

