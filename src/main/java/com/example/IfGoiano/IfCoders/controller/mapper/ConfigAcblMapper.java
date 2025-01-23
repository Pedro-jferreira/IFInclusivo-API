package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigAcblMapper {

    @Autowired
    private ModelMapper modelMapper;

    // De Entity para InputDTO
    public ConfigAcblInputDTO toConfigAcblInputDTO(ConfigAcessibilidadeEntity configAcblEntity) {
        return modelMapper.map(configAcblEntity, ConfigAcblInputDTO.class);
    }

    // De InputDTO para Entity
    public ConfigAcessibilidadeEntity toConfigAcblEntity(ConfigAcblInputDTO configAcblInputDTO) {
        return modelMapper.map(configAcblInputDTO, ConfigAcessibilidadeEntity.class);
    }

    // De Entity para OutputDTO
    public ConfigAcblOutputDTO toConfigAcblOutputDTO(ConfigAcessibilidadeEntity configAcblEntity) {
        return modelMapper.map(configAcblEntity, ConfigAcblOutputDTO.class);
    }

    // De OutputDTO para Entity
    public ConfigAcessibilidadeEntity toConfigAcblEntity(ConfigAcblOutputDTO configAcblOutputDTO) {
        return modelMapper.map(configAcblOutputDTO, ConfigAcessibilidadeEntity.class);
    }

    // Atualiza a Entity com base no InputDTO
    public void updateConfigAcblEntityFromDTO(ConfigAcblInputDTO configAcblInputDTO, ConfigAcessibilidadeEntity configAcblEntity) {
        modelMapper.map(configAcblInputDTO, configAcblEntity);
    }
}

