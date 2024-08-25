package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAll() {
        try {
            return usuarioRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all users: " + e);
        }

    }

    public UsuarioEntity findById(Long id) {
        try {
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
            return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public UsuarioEntity save(UsuarioEntity usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new user: " + e);
        }
    }

    @Transactional
    public UsuarioEntity update(Long id, UsuarioEntity usuarioDetails) {
        try {
            UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateUsuarioDetails(usuario, usuarioDetails);
            return usuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the user: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            UsuarioEntity usuario = findById(id);
            usuarioRepository.delete(usuario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the user: " + e);
        }
    }

    public void updateUsuarioDetails (UsuarioEntity usuario, UsuarioEntity usuarioDetails){
        usuario.setNome(usuarioDetails.getNome());
        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setMatricula(usuarioDetails.getMatricula());
        usuario.setBiografia(usuarioDetails.getBiografia());

    }
}
