package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigAcessibilidadeServiceImpl {
    @Autowired
    private ConfigAcessibilidadeRepository configAcessibilidadeRepository;

    public List<ConfigAcessibilidadeEntity> findAll() {
        try {
            return configAcessibilidadeRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all config acessibilidades: " + e);
        }
    }

    public ConfigAcessibilidadeEntity findById(Long id) {
        try {
            Optional<ConfigAcessibilidadeEntity> configAcessibilidade = configAcessibilidadeRepository.findById(id);
            return configAcessibilidade.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcessibilidadeEntity save(ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        try {
            return configAcessibilidadeRepository.save(configAcessibilidadeEntity);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcessibilidadeEntity update(Long id, ConfigAcessibilidadeEntity configAcessibilidadeEntityDetails) {
        try {
            ConfigAcessibilidadeEntity configAcessibilidadeEntity = configAcessibilidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateConfigAcessibilidadeDetails(configAcessibilidadeEntity, configAcessibilidadeEntityDetails);
            return configAcessibilidadeRepository.save(configAcessibilidadeEntity);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the config acessibilidade: " + e);
        }
    }

    public void delete(Long id) {
        try {
            ConfigAcessibilidadeEntity configAcessibilidadeEntity = findById(id);
            configAcessibilidadeRepository.delete(configAcessibilidadeEntity);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the config acessibilidade: " + e);
        }
    }

    public void updateConfigAcessibilidadeDetails (ConfigAcessibilidadeEntity configAcessibilidadeEntity, ConfigAcessibilidadeEntity configAcessibilidadeEntityDetails){
        configAcessibilidadeEntity.setAudicao(configAcessibilidadeEntityDetails.getAudicao());
        configAcessibilidadeEntity.setTema(configAcessibilidadeEntityDetails.getTema());
        configAcessibilidadeEntity.setZoom(configAcessibilidadeEntityDetails.getZoom());
    }
}
