package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;

import java.util.List;

public interface ConfigAcessibilidadeService {
    List<ConfigAcblOutputDTO> findAll();

    ConfigAcblOutputDTO findById(Long id);

    ConfigAcblOutputDTO save(ConfigAcblInputDTO configAcessibilidadeEntity, Long userId);

    ConfigAcblOutputDTO update(Long id, ConfigAcblInputDTO configAcessibilidadeEntityDetails);

    void delete(Long id);
}
