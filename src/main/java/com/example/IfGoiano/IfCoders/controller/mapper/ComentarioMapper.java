package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PublicacaoMapper.class, ComentarioMapper.class})
public interface ComentarioMapper {

    // Mapeamento de ComentarioEntity para SimpleComentarioDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    SimpleComentarioDTO toSimpleComentarioDTO(ComentarioEntity entity);

    // Mapeamento de SimpleComentarioDTO para ComentarioEntity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    ComentarioEntity toEntity(SimpleComentarioDTO dto);

    // Mapeamento de ComentarioEntity para ComentarioInputDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "publicacaoEntity", target = "publicacao")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "comentarioPai", target = "comentarioPai")
    ComentarioInputDTO toComentarioInputDTO(ComentarioEntity entity);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "usuario", target = "usuario")
//    @Mapping(source = "publicacao", target = "publicacaoEntity")
//    @Mapping(source = "content", target = "content")
//    @Mapping(source = "publicacao", target = "comentarioPai")
//    ComentarioEntity toComentarioEntity(ComentarioInputDTO dto);
//
//    @Mapping(source = "usuario",target = "usuario")
//    @Mapping(source = "publicacaoEntity",target = "publicacaoEntity")
//    @Mapping(source = "comentarioPai",target = "comentarioPai")
//    @Mapping(source = "comentariosFilhos",target = "comentariosFilhos")
//    @Mapping(source = "usefulBy",target = "usefulBy")
//    ComentarioOutputDTO toComentarioOutputDTO(ComentarioEntity entity);


}