package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoNapneDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlunoNapneMapper {

    @Autowired
    ModelMapper modelMapper;

    public SimpleAlunoNapneDTO toSimpleAlunoNapneDTO(AlunoNapneEntity alunoNapneEntitty){
        return modelMapper.map(alunoNapneEntitty, SimpleAlunoNapneDTO.class);
    }

    public AlunoNapneInputDTO toAlunoNapneInputDTO(AlunoNapneEntity alunoNapneEntity){
        return modelMapper.map(alunoNapneEntity, AlunoNapneInputDTO.class);
    }
    public AlunoNapneEntity toAlunoNapneEntity(AlunoNapneInputDTO inputDTO){
        return modelMapper.map(inputDTO, AlunoNapneEntity.class);
    }

    public AlunoNapneOutputDTO toAlunoNapneOutputDTO(AlunoNapneEntity alunoNapneEntity){
        return modelMapper.map(alunoNapneEntity, AlunoNapneOutputDTO.class);
    }

    public void updateAlunoNapneEntiryFromDTO(AlunoNapneInputDTO alunoDetails, AlunoNapneEntity alunoEntity) {
        modelMapper.map(alunoDetails, alunoEntity);
    }

    public UsuarioEntity toAlunoNapneEntity(AlunoNapneOutputDTO alunoNapne) {
        return modelMapper.map(alunoNapne, UsuarioEntity.class);
    }
}
