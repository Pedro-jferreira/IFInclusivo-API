package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper mapper;

    public List<UsuarioOutputDTO> findAll() {
        try {
            return usuarioRepository.findAll().stream().map(mapper::toUsuarioOutputDTO).collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all users: " + e);
        }

    }

    public UsuarioOutputDTO findById(Long id) {
        try {
            var usuario = usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
            return mapper.toUsuarioOutputDTO(usuario);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public UsuarioOutputDTO save(UsuarioInputDTO usuario) {
        try {
            usuarioRepository.save(mapper.toUsuarioEntity(usuario));
            return findById(usuario.getId());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new user: " + e);
        }
    }

    @Transactional
    public UsuarioOutputDTO update(Long id, UsuarioInputDTO usuarioDetails) {
        try {
            var usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            mapper.updateUsuarioEntityFromDTO(usuarioDetails,usuario);
            return mapper.toUsuarioOutputDTO(usuarioRepository.save(usuario));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the user: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            usuarioRepository.delete(mapper.toUsuarioEntity(findById(id)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the user: " + e);
        }
    }


}
