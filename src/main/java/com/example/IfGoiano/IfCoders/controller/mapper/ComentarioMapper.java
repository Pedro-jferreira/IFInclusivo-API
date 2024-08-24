package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {PublicacaoMapper.class})
public interface ComentarioMapper {
    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    @Mapping(source = "publicacao", target = "publicacaoDTO")
    @Mapping(source = "comentarioPai", target = "comentarioPaiDTO")
    ComentarioDTO comentarioToComentarioDTO(ComentarioEntity comentario);

    @Mapping(source = "publicacaoDTO", target = "publicacao")
    @Mapping(source = "comentarioPaiDTO", target = "comentarioPai")
    ComentarioEntity comentarioDTOToComentario(ComentarioDTO comentarioDTO);

    @Mapping(source = "publicacaoDTO", target = "publicacao")
    @Mapping(source = "comentarioPaiDTO", target = "comentarioPai")
    void updateComentarioFromDTO(ComentarioDTO comentarioDTO, @MappingTarget ComentarioEntity comentario);
}
