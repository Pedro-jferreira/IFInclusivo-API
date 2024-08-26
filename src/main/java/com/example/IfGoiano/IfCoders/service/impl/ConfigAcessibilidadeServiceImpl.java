package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigAcessibilidadeServiceImpl {
    @Autowired
    private ConfigAcessibilidadeRepository repository;
    @Autowired
    ConfigAcblMapper mapper;

    public List<ConfigAcblOutputDTO> findAll() {
        try {
            return repository.findAll().stream().map(mapper::toConfigAcblOutputDTO).collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all config acessibilidades: " + e);
        }
    }

    public ConfigAcblOutputDTO findById(Long id) {
        try {
            var configAcessibilidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            return mapper.toConfigAcblOutputDTO(configAcessibilidade);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcblOutputDTO save(ConfigAcblInputDTO configAcessibilidadeEntity) {
        try {
             repository.save(mapper.toConfigAcblEntity(configAcessibilidadeEntity));
             return findById(configAcessibilidadeEntity.getId());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcblOutputDTO update(Long id, ConfigAcblInputDTO configAcessibilidadeEntityDetails) {
        try {
            var configAcbl = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            mapper.updateConfigAcblEntityFromDTO(configAcessibilidadeEntityDetails, configAcbl);
            return mapper.toConfigAcblOutputDTO(repository.save(configAcbl));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the config acessibilidade: " + e);
        }
    }

    public void delete(Long id) {
        try {
            repository.delete(mapper.toConfigAcblEntity(findById(id)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the config acessibilidade: " + e);
        }
    }

}
