package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleCursoDTO;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    @Autowired
    ModelMapper modelMapper;

    public SimpleCursoDTO toSimpleCursoDTO(CursoEntity cursoEntity){
        return modelMapper.map(cursoEntity, SimpleCursoDTO.class);
    }
    public CursoEntity toCursoEntity(SimpleCursoDTO simpleCursoDTO){
        return modelMapper.map(simpleCursoDTO, CursoEntity.class);
    }
    public void updateCursoEntiryFromDTO(SimpleCursoDTO CursoDeitals, CursoEntity cursoEntity) {
        modelMapper.map(CursoDeitals, cursoEntity);
    }
}