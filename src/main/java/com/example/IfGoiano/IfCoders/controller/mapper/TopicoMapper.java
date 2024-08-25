package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);


    TopicoOutputDTO topicoToTopicoDTO(TopicoEntity topicoEntity);

    TopicoEntity topicoDTOToTopico(TopicoOutputDTO topicoDTO);

    void updateTopicoFromDTO(TopicoOutputDTO topicoDTO, @MappingTarget TopicoEntity topicoEntity);
}