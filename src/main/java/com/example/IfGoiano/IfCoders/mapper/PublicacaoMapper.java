package com.example.IfGoiano.IfCoders.mapper;

import com.example.IfGoiano.IfCoders.DTO.PublicacaoDTO;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring",uses = {TopicoMapper.class, LikeMapper.class})
public interface PublicacaoMapper {
    PublicacaoMapper INSTANCE = Mappers.getMapper(PublicacaoMapper.class);

    @Mapping(source = "topico", target = "topicoDTO")
    @Mapping(source = "likes", target = "likeDTOS")
    @Mapping(source = "localDateTime", target = "localDateTime")
    PublicacaoDTO publicacaoToPublicacaoDTO(Publicacao publicacao);

    @Mapping(source = "topicoDTO", target = "topico")
    @Mapping(source = "likeDTOS", target = "likes")
    @Mapping(source = "localDateTime", target = "localDateTime")
    Publicacao publicacaoDTOToPublicacao(PublicacaoDTO publicacaoDTO);

    @Mapping(source = "topicoDTO", target = "topico")
    @Mapping(source = "likeDTOS", target = "likes")
    @Mapping(source = "localDateTime", target = "localDateTime")
    void updatePublicacaoFromDTO(PublicacaoDTO publicacaoDTO, @MappingTarget Publicacao publicacao);
}
