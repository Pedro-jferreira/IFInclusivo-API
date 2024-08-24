package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.ConfigAcessibilidade;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigAcessibilidadeService {
    @Autowired
    private ConfigAcessibilidadeRepository configAcessibilidadeRepository;

    public List<ConfigAcessibilidade> findAll() {
        try {
            return configAcessibilidadeRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all config acessibilidades: " + e);
        }
    }

    public ConfigAcessibilidade findById(Long id) {
        try {
            Optional<ConfigAcessibilidade> configAcessibilidade = configAcessibilidadeRepository.findById(id);
            return configAcessibilidade.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcessibilidade save(ConfigAcessibilidade configAcessibilidade) {
        try {
            return configAcessibilidadeRepository.save(configAcessibilidade);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new config acessibilidade: " + e);
        }
    }

    @Transactional
    public ConfigAcessibilidade update(Long id, ConfigAcessibilidade configAcessibilidadeDetails) {
        try {
            ConfigAcessibilidade configAcessibilidade = configAcessibilidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateConfigAcessibilidadeDetails(configAcessibilidade, configAcessibilidadeDetails);
            return configAcessibilidadeRepository.save(configAcessibilidade);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the config acessibilidade: " + e);
        }
    }

    public void delete(Long id) {
        try {
            ConfigAcessibilidade configAcessibilidade = findById(id);
            configAcessibilidadeRepository.delete(configAcessibilidade);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the config acessibilidade: " + e);
        }
    }

    public void updateConfigAcessibilidadeDetails (ConfigAcessibilidade configAcessibilidade, ConfigAcessibilidade configAcessibilidadeDetails){
        configAcessibilidade.setAudicao(configAcessibilidadeDetails.getAudicao());
        configAcessibilidade.setTema(configAcessibilidadeDetails.getTema());
        configAcessibilidade.setZoom(configAcessibilidadeDetails.getZoom());
    }
}
