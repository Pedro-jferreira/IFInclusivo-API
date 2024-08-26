package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    @Autowired
    ModelMapper modelMapper;

    public CursoInputDTO toSimpleCursoDTO(CursoEntity cursoEntity){
        return modelMapper.map(cursoEntity, CursoInputDTO.class);
    }
    public CursoEntity toCursoEntity(CursoInputDTO cursoInputDTO){
        return modelMapper.map(cursoInputDTO, CursoEntity.class);
    }
    public CursoOutputDTO toCursoOutputDTO(CursoEntity cursoEntity){
        return modelMapper.map(cursoEntity, CursoOutputDTO.class);
    }

    public CursoEntity toCursoEntity(CursoOutputDTO cursoOutputDTO){
        return modelMapper.map(cursoOutputDTO, CursoEntity.class);
    }
    public void updateCursoEntiryFromDTO(CursoInputDTO CursoDeitals, CursoEntity cursoEntity) {
        modelMapper.map(CursoDeitals, cursoEntity);
    }
}