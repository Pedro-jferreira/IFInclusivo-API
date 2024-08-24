package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.List;

public interface ConfigAcessibilidadeService {
    List<ConfigAcessibilidadeEntity> findAll();

    ConfigAcessibilidadeEntity findById(Long id);

    ConfigAcessibilidadeEntity save(ConfigAcessibilidadeEntity configAcessibilidade);

    ConfigAcessibilidadeEntity update(Long id, ConfigAcessibilidadeEntity configAcessibilidadeDetails);

    void delete(Long id);
}
