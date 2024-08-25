package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleInterpreteDTOSimple;
import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterpreteMapper {

    @Autowired
    ModelMapper modelMapper;


    public SimpleInterpreteDTOSimple toSimpleInterpreteDTO(InterpreteEntity interpreteEntity){
        return modelMapper.map(interpreteEntity, SimpleInterpreteDTOSimple.class);
    }

    public InterpreteEntity toInterpreteEntity(InterpreteInputDTO inputInterpreteDTO){
        return modelMapper.map(inputInterpreteDTO, InterpreteEntity.class);
    }

    public InterpreteInputDTO toInterpreteInputDTO(InterpreteEntity interpreteEntity){
        return modelMapper.map(interpreteEntity, InterpreteInputDTO.class);
    }

    public InterpreteOutputDTO toInterpreteOutputDTO(InterpreteEntity interpreteEntity){
        return modelMapper.map(interpreteEntity, InterpreteOutputDTO.class);
    }

}
