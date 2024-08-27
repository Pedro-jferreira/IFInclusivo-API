package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigAcessibilidadeServiceImpl implements ConfigAcessibilidadeService {
    @Autowired
    private ConfigAcessibilidadeRepository repository;
    @Autowired
    ConfigAcblMapper mapper;

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
    public ConfigAcblOutputDTO save(ConfigAcblInputDTO configAcessibilidadeEntity) {
         repository.save(mapper.toConfigAcblEntity(configAcessibilidadeEntity));
         return findById(configAcessibilidadeEntity.getId());
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
