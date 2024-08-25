package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoMapper {

    @Autowired
    ModelMapper modelMapper;

    public SimpleTopicoDTO toSimpleTopicoDTO(TopicoEntity topicoEntity) {
        return modelMapper.map(topicoEntity, SimpleTopicoDTO.class);
    }
    public TopicoEntity toTopicoEntity(SimpleTopicoDTO simpleTopicoDTO) {
        return modelMapper.map(simpleTopicoDTO, TopicoEntity.class);
    }

    public TopicoInputDTO toTopicoInputDTO(TopicoEntity topicoEntity) {
        return modelMapper.map(topicoEntity, TopicoInputDTO.class);
    }
    public TopicoEntity toTopicoEntity(TopicoInputDTO inputDTO) {
        return modelMapper.map(inputDTO, TopicoEntity.class);
    }

    public TopicoOutputDTO toTopicoOutputDTO(TopicoEntity topicoEntity) {
        return modelMapper.map(topicoEntity, TopicoOutputDTO.class);
    }
    public TopicoEntity toTopicoEntity(TopicoOutputDTO inputDTO) {
        return modelMapper.map(inputDTO, TopicoEntity.class);
    }

    public void updateTopicoEntityFromDTO(TopicoInputDTO topicoDeitailsDTO, TopicoEntity topicoEntity) {
        modelMapper.map(topicoDeitailsDTO, topicoEntity);
    }

}