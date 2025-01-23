package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioMapper mapper;


    @Override
    public List<UsuarioOutputDTO> findAll() {
        return repository.findAll().stream().map(mapper::toOutputDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioOutputDTO findById(Long id) {
        return mapper.toOutputDTO(repository.findById(id).get());
    }

    @Override
    public UsuarioOutputDTO save(UsuarioInputDTO usuarioId) {
        UsuarioEntity usuarioEntity = mapper.toEntity(usuarioId);
        return findById(repository.save(usuarioEntity).getId());
    }

    @Override
    public UsuarioOutputDTO update(UsuarioInputDTO usuarioDetails, Long id) {
        Optional<UsuarioEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            mapper.updateUsuarioEntityFromDTO(usuarioDetails, optional.get());
            return mapper.toOutputDTO(repository.save(optional.get()));
        }else throw new ResourceNotFoundException("usuario nao disponivel");

    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
