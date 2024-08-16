package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.ConfigAcessibilidade;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConfigAcessibilidadeService {
    private final ConfigAcessibilidadeRepository configAcessibilidadeRepository;

    @Autowired
    public ConfigAcessibilidadeService(ConfigAcessibilidadeRepository configAcessibilidadeRepository) {
        this.configAcessibilidadeRepository = configAcessibilidadeRepository;
    }

    public List<ConfigAcessibilidade> findAllConfigsAcessibilidade() {
        return configAcessibilidadeRepository.findAll();
    }

    public ConfigAcessibilidade findConfigAcessibilidadeById(Long id) {
        return configAcessibilidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public ConfigAcessibilidade saveConfigAcessibilidade(ConfigAcessibilidade configAcessibilidade) {
        // Implementar lógica de validação, se necessário
        return configAcessibilidadeRepository.save(configAcessibilidade);
    }

    public ConfigAcessibilidade updateConfigAcessibilidade(Long id, ConfigAcessibilidade configAcessibilidadeAtualizado) {
        ConfigAcessibilidade configAcessibilidade = configAcessibilidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        configAcessibilidade.setAudicao(configAcessibilidadeAtualizado.getAudicao());
        configAcessibilidade.setTema(configAcessibilidadeAtualizado.getTema());
        configAcessibilidade.setZoom(configAcessibilidadeAtualizado.getZoom());

        return configAcessibilidadeRepository.save(configAcessibilidade);
    }

    public void deleteConfigAcessibilidade(Long id) {
        if (!configAcessibilidadeRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        configAcessibilidadeRepository.deleteById(id);
    }
}
