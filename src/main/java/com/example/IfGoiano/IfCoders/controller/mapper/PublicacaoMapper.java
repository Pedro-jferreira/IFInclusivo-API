package com.example.IfGoiano.IfCoders.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring",uses = {TopicoMapper.class})
public interface PublicacaoMapper {
    PublicacaoMapper INSTANCE = Mappers.getMapper(PublicacaoMapper.class);

}
