package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleLibrasDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibrasMapper {

    @Autowired
    ModelMapper modelMapper;

    public SimpleLibrasDTO toSimpleLibraDTO(LibrasEntity librasEntity){
        return modelMapper.map(librasEntity, SimpleLibrasDTO.class);
    }
    public LibrasEntity toLibrasEntity(SimpleLibrasDTO simpleLibrasDTO){
        return modelMapper.map(simpleLibrasDTO, LibrasEntity.class);
    }

    public LibrasInputDTO toLibrasInputDTO(LibrasEntity librasEntity){
        return modelMapper.map(librasEntity, LibrasInputDTO.class);
    }
    public LibrasEntity toLibrasEntity(LibrasInputDTO inputDTO){
        return modelMapper.map(inputDTO, LibrasEntity.class);
    }

    public LibrasOutputDTO toLibrasOutputDTO(LibrasEntity librasEntity){
        return modelMapper.map(librasEntity, LibrasOutputDTO.class);
    }
    public LibrasEntity toLibrasEntity(LibrasOutputDTO outputDTO){
        return modelMapper.map(outputDTO, LibrasEntity.class);
    }

    public void updateLibrasEntityFromDTO(LibrasInputDTO inputDTO, LibrasEntity librasEntity){
        modelMapper.map(inputDTO, librasEntity);
    }

}
