package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorMapper {

    @Autowired
    ModelMapper modelMapper;

    public TutorEntity toTutorEntity(TutorInputDTO tutorInput) {

        return modelMapper.map(tutorInput, TutorEntity.class);
    }
    public TutorEntity toTutorEntity(TutorOutputDTO tutorOutput) {
        return modelMapper.map(tutorOutput, TutorEntity.class);
    }

    public TutorInputDTO toTutorInputDTO(TutorEntity tutorEntity) {
        return modelMapper.map(tutorEntity, TutorInputDTO.class);
    }

    public TutorOutputDTO toTutorOutputDTO(TutorEntity tutorEntity) {
        return modelMapper.map(tutorEntity, TutorOutputDTO.class);
    }
    public void updateTutorEntityFromDTO(TutorInputDTO tutoDeitails, TutorEntity tutorEntity) {
        modelMapper.map(tutoDeitails, tutorEntity);
    }
}
