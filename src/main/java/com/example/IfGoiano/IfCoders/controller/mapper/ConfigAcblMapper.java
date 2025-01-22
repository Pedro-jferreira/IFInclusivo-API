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
        ConfigAcblOutputDTO dto = new ConfigAcblOutputDTO();
        dto.setId(configAcblEntity.getId());
        dto.setZoom(configAcblEntity.getZoom());
        dto.setTema(configAcblEntity.getTema());
        dto.setAudicao(configAcblEntity.getAudicao());
        return dto;
    }

    // De OutputDTO para Entity
    public ConfigAcessibilidadeEntity toConfigAcblEntity(ConfigAcblOutputDTO configAcblOutputDTO) {
        ConfigAcessibilidadeEntity entity = new ConfigAcessibilidadeEntity();
        entity.setId(configAcblOutputDTO.getId());
        entity.setTema(configAcblOutputDTO.getTema());
        entity.setZoom(configAcblOutputDTO.getZoom());
        entity.setAudicao(configAcblOutputDTO.getAudicao());
        return entity;
    }

    // Atualiza a Entity com base no InputDTO
    public void updateConfigAcblEntityFromDTO(ConfigAcblInputDTO configAcblInputDTO, ConfigAcessibilidadeEntity configAcblEntity) {
        modelMapper.map(configAcblInputDTO, configAcblEntity);
    }
}

