package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import org.springframework.context.annotation.Lazy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    @Autowired
    ModelMapper modelMapper;
    @Lazy
    @Autowired
    AlunoMapper alunoMapper;

    public CursoInputDTO toSimpleCursoDTO(CursoEntity cursoEntity){
        return modelMapper.map(cursoEntity, CursoInputDTO.class);
    }
    public CursoEntity toCursoEntity(CursoInputDTO cursoInputDTO){
        CursoEntity entity = new CursoEntity();
        entity.setNome(cursoInputDTO.getNome());
        return entity;
    }
    public CursoOutputDTO toCursoOutputDTO(CursoEntity cursoEntity){
        CursoOutputDTO dto = new CursoOutputDTO();
        dto.setId(cursoEntity.getId());
        dto.setNome(cursoEntity.getNome());
        if(!cursoEntity.getAlunos().isEmpty()){
            for (AlunoEntity alunoEntity : cursoEntity.getAlunos()){
                dto.getAlunos().add(alunoMapper.toSimpleAlunoDTO(alunoEntity));
            }
        }

        return dto;
    }


    public CursoEntity toCursoEntity(CursoOutputDTO cursoOutputDTO){
        CursoEntity entity = new CursoEntity();
        entity.setId(cursoOutputDTO.getId());
        entity.setNome(cursoOutputDTO.getNome());
        if(!cursoOutputDTO.getAlunos().isEmpty()){
            for (SimpleAlunoDTO current:cursoOutputDTO.getAlunos()) {
                entity.getAlunos().add(alunoMapper.toAlunoEntity(current));
            }
        }
        return entity;
    }
    public void updateCursoEntiryFromDTO(CursoInputDTO CursoDeitals, CursoEntity cursoEntity) {
        modelMapper.map(CursoDeitals, cursoEntity);
    }
}