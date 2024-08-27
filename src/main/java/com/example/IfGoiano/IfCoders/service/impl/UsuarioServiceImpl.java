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

    @Override
    public List<UsuarioOutputDTO> findAll() {
        return usuarioRepository.findAll().stream().map(mapper::toUsuarioOutputDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioOutputDTO findById(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return mapper.toUsuarioOutputDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioOutputDTO save(UsuarioInputDTO usuario) {
        usuarioRepository.save(mapper.toUsuarioEntity(usuario));
        return findById(usuario.getId());
    }

    @Override
    @Transactional
    public UsuarioOutputDTO update(Long id, UsuarioInputDTO usuarioDetails) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateUsuarioEntityFromDTO(usuarioDetails,usuario);
        return mapper.toUsuarioOutputDTO(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.delete(mapper.toUsuarioEntity(findById(id)));
    }
}
