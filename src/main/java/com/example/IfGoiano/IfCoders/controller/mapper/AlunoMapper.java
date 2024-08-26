package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Métodos de mapeamento
    public SimpleAlunoDTO toSimpleAlunoDTO(AlunoEntity alunoEntity) {
        return modelMapper.map(alunoEntity, SimpleAlunoDTO.class);
    }

    public AlunoEntity toAlunoEntity(SimpleAlunoDTO simpleAlunoDTO) {
        return modelMapper.map(simpleAlunoDTO, AlunoEntity.class);
    }

    public AlunoInputDTO toAlunoInputDTO(AlunoEntity alunoEntity) {
        return modelMapper.map(alunoEntity, AlunoInputDTO.class);
    }

    public AlunoEntity toAlunoEntity(AlunoInputDTO alunoInputDTO) {
        return modelMapper.map(alunoInputDTO, AlunoEntity.class);
    }

    public AlunoOutputDTO toAlunoOutputDTO(AlunoEntity alunoEntity) {
        return modelMapper.map(alunoEntity, AlunoOutputDTO.class);
    }

    public AlunoEntity toAlunoEntity(AlunoOutputDTO alunoOutputDTO) {
        return modelMapper.map(alunoOutputDTO, AlunoEntity.class);
    }

    public void updateAlunoEntityFromDTO(AlunoInputDTO alunoDetails, AlunoEntity alunoEntity) {
        modelMapper.map(alunoDetails, alunoEntity);
    }
}


