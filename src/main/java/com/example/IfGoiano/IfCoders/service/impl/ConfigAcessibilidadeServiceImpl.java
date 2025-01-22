package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigAcessibilidadeServiceImpl implements ConfigAcessibilidadeService {
    @Autowired
    private ConfigAcessibilidadeRepository repository;
    @Autowired
    ConfigAcblMapper mapper;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<ConfigAcblOutputDTO> findAll() {
        return repository.findAll().stream().map(mapper::toConfigAcblOutputDTO).collect(Collectors.toList());
    }

    @Override
    public ConfigAcblOutputDTO findById(Long id) {
        var configAcessibilidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toConfigAcblOutputDTO(configAcessibilidade);
    }

    @Override
    @Transactional
    public ConfigAcblOutputDTO save(ConfigAcblInputDTO configAcblInputDTO, Long userId) {

        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + userId + " não encontrado!"));
        ConfigAcessibilidadeEntity entity = mapper.toConfigAcblEntity(configAcblInputDTO);
        entity.setUsuario(usuario);
        ConfigAcessibilidadeEntity savedEntity = repository.save(entity);
        return mapper.toConfigAcblOutputDTO(savedEntity);
    }

    @Override
    @Transactional
    public ConfigAcblOutputDTO update(Long id, ConfigAcblInputDTO configAcessibilidadeEntityDetails) {
        var configAcbl = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateConfigAcblEntityFromDTO(configAcessibilidadeEntityDetails, configAcbl);
        return mapper.toConfigAcblOutputDTO(repository.save(configAcbl));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(mapper.toConfigAcblEntity(findById(id)));
    }

}
