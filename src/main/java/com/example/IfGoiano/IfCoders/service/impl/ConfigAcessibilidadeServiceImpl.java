package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigAcessibilidadeServiceImpl implements ConfigAcessibilidadeService {

    @Autowired
    private ConfigAcessibilidadeRepository configAcessibilidadeRepository;

    public List<ConfigAcessibilidadeEntity> findAll() {
        return configAcessibilidadeRepository.findAll();
    }

    public ConfigAcessibilidadeEntity findById(Long id) {
        Optional<ConfigAcessibilidadeEntity> configAcessibilidade = configAcessibilidadeRepository.findById(id);
        return configAcessibilidade.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public ConfigAcessibilidadeEntity save(ConfigAcessibilidadeEntity configAcessibilidade) {
        return configAcessibilidadeRepository.save(configAcessibilidade);
    }

    @Transactional
    public ConfigAcessibilidadeEntity update(Long id, ConfigAcessibilidadeEntity configAcessibilidadeDetails) {
        ConfigAcessibilidadeEntity configAcessibilidade = configAcessibilidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateConfigAcessibilidadeDetails(configAcessibilidade, configAcessibilidadeDetails);
        return configAcessibilidadeRepository.save(configAcessibilidade);
    }

    @Transactional
    public void delete(Long id) {
        ConfigAcessibilidadeEntity configAcessibilidade = findById(id);
        configAcessibilidadeRepository.delete(configAcessibilidade);
    }

    private void updateConfigAcessibilidadeDetails (ConfigAcessibilidadeEntity configAcessibilidade, ConfigAcessibilidadeEntity configAcessibilidadeDetails){
        configAcessibilidade.setAudicao(configAcessibilidadeDetails.getAudicao());
        configAcessibilidade.setTema(configAcessibilidadeDetails.getTema());
        configAcessibilidade.setZoom(configAcessibilidadeDetails.getZoom());
    }
}
