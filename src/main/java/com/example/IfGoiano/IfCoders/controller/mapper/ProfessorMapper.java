package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {
    @Autowired
    ModelMapper modelMapper;


    public ProfessorEntity toProfessorEntity(ProfessorInputDTO professorInputDTO){
        return modelMapper.map(professorInputDTO, ProfessorEntity.class);
    }
    public ProfessorEntity toProfessorEntity(ProfessorOutputDTO professorOutputDTO){
        return modelMapper.map(professorOutputDTO, ProfessorEntity.class);
    }

    public ProfessorOutputDTO toProfessorOutputDTO(ProfessorEntity professorEntity){
        return modelMapper.map(professorEntity, ProfessorOutputDTO.class);
    }

    public SimpleProfessorDTO toSimpleProfessorDTO(ProfessorEntity professorEntity){
        return modelMapper.map(professorEntity, SimpleProfessorDTO.class);
    }

    public void updateProfessorEntityFromDTO(ProfessorInputDTO professorDeitails, ProfessorEntity professorEntity){
        modelMapper.map(professorDeitails, professorEntity);

    }


}
