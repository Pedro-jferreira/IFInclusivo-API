package com.example.IfGoiano.IfCoders.mapper;

import com.example.IfGoiano.IfCoders.DTO.ResolveuProblemaDTO;
import com.example.IfGoiano.IfCoders.model.ResolveuProblema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResolveuProblemaMapper {
    ResolveuProblemaMapper INSTANCE = Mappers.getMapper(ResolveuProblemaMapper.class);

    @Mapping(source = "dataHoraVoto", target = "localDateTime")
    ResolveuProblemaDTO resolveuProblemaToResolveuProblemaDTO(ResolveuProblema resolveuProblema);


    @Mapping(source = "localDateTime", target = "dataHoraVoto")
    ResolveuProblema resolveuProblemaDTOToResolveuProblema(ResolveuProblemaDTO resolveuProblemaDTO);


    @Mapping(source = "localDateTime", target = "dataHoraVoto")
    void updateResolveuProblemaFromDTO(ResolveuProblemaDTO resolveuProblemaDTO, @MappingTarget ResolveuProblema resolveuProblema);

}