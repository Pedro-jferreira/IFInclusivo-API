package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring",uses = {TopicoMapper.class})
public interface PublicacaoMapper {
    PublicacaoMapper INSTANCE = Mappers.getMapper(PublicacaoMapper.class);

    @Mapping(source = "topico", target = "topicoDTO")
    @Mapping(source = "localDateTime", target = "localDateTime")
    PublicacaoDTO publicacaoToPublicacaoDTO(PublicacaoEntity publicacaoEntity);

    @Mapping(source = "topicoDTO", target = "topico")
    @Mapping(source = "localDateTime", target = "localDateTime")
    PublicacaoEntity publicacaoDTOToPublicacao(PublicacaoDTO publicacaoDTO);

    @Mapping(source = "topicoDTO", target = "topico")
    @Mapping(source = "localDateTime", target = "localDateTime")
    void updatePublicacaoFromDTO(PublicacaoDTO publicacaoDTO, @MappingTarget PublicacaoEntity publicacaoEntity);
}
