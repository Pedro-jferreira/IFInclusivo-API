package com.example.IfGoiano.IfCoders.mapper;

import com.example.IfGoiano.IfCoders.DTO.LikeDTO;
import com.example.IfGoiano.IfCoders.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);


    @Mapping(source = "dataHora", target = "localDateTime")
    LikeDTO likeToLikeDTO(Like like);


    @Mapping(source = "localDateTime", target = "dataHora")
    Like likeDTOToLike(LikeDTO likeDTO);

    @Mapping(source = "localDateTime", target = "dataHora")
    void updateLikeFromDTO(LikeDTO likeDTO, @MappingTarget Like like);
}
