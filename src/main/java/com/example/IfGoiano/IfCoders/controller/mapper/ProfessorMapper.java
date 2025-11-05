package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.ProfessorUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        ProfessorOutputDTO p = new ProfessorOutputDTO();
        p.setId(professorEntity.getId());
        p.setNome(professorEntity.getNome());
        p.setBiografia(professorEntity.getBiografia());
        p.setImgPerfil(professorEntity.getImgPerfil());
        p.setLogin(professorEntity.getLogin());
        p.setMatricula(professorEntity.getMatricula());
        p.setDataCriacao(professorEntity.getDataCriacao());
        p.setFormacao(professorEntity.getFormacao());
        ConfigAcblOutputDTO a = new ConfigAcblOutputDTO();
        a.setId(professorEntity.getConfigAcessibilidadeEntity().getId());
        a.setTema(professorEntity.getConfigAcessibilidadeEntity().getTema());
        a.setAudicao(professorEntity.getConfigAcessibilidadeEntity().getAudicao());
        a.setZoom(professorEntity.getConfigAcessibilidadeEntity().getZoom());
        p.setConfigAcessibilidadeEntity(a);



        return p;
    }

    public SimpleProfessorDTO toSimpleProfessorDTO(ProfessorEntity professorEntity){
        return modelMapper.map(professorEntity, SimpleProfessorDTO.class);
    }

    public void updateProfessorEntityFromDTO(ProfessorUpdateDTO professorDeitails, ProfessorEntity professorEntity){
        modelMapper.map(professorDeitails, professorEntity);

    }


}
