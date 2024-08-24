package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoDTO;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);


    TopicoDTO topicoToTopicoDTO(TopicoEntity topicoEntity);

    TopicoEntity topicoDTOToTopico(TopicoDTO topicoDTO);

    void updateTopicoFromDTO(TopicoDTO topicoDTO, @MappingTarget TopicoEntity topicoEntity);
}