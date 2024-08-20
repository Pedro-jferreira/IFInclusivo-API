package com.example.IfGoiano.IfCoders.mapper;

import com.example.IfGoiano.IfCoders.DTO.TopicoDTO;
import com.example.IfGoiano.IfCoders.model.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);


    TopicoDTO topicoToTopicoDTO(Topico topico);

    Topico topicoDTOToTopico(TopicoDTO topicoDTO);

    void updateTopicoFromDTO(TopicoDTO topicoDTO, @MappingTarget Topico topico);
}