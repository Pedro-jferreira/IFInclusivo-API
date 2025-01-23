package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorMapper {
    @Autowired
    ModelMapper modelMapper;
    @Lazy
    @Autowired
    TopicoMapper topicoMapper;
    @Autowired
    ConfigAcblMapper configAcblMapper;


    public ProfessorEntity toProfessorEntity(ProfessorInputDTO professorInputDTO){
        ProfessorEntity entity = new ProfessorEntity();
        entity.setNome(professorInputDTO.getNome());
        entity.setLogin(professorInputDTO.getLogin());
        entity.setSenha(professorInputDTO.getSenha());
        entity.setMatricula(professorInputDTO.getMatricula());
        entity.setBiografia(professorInputDTO.getBiografia());
        entity.setFormacao(professorInputDTO.getFormacao());
        return entity;
    }

    public ProfessorEntity toProfessorEntity(ProfessorOutputDTO professorOutputDTO){
        return modelMapper.map(professorOutputDTO, ProfessorEntity.class);
    }

    public ProfessorOutputDTO toProfessorOutputDTO(ProfessorEntity professorEntity){
        ProfessorOutputDTO p = new ProfessorOutputDTO();
        p.setId(professorEntity.getId());
        p.setNome(professorEntity.getNome());
        p.setBiografia(professorEntity.getBiografia());
        p.setLogin(professorEntity.getLogin());
        p.setSenha(professorEntity.getSenha());
        p.setMatricula(professorEntity.getMatricula());
        p.setDataCriacao(professorEntity.getDataCriacao());
        p.setFormacao(professorEntity.getFormacao());

        if (professorEntity.getConfigAcessibilidadeEntity()!= null) {
            p.setConfigAcessibilidadeEntity(configAcblMapper.
                    toConfigAcblOutputDTO(professorEntity.
                            getConfigAcessibilidadeEntity()));
        }

        if (!professorEntity.getTopicos().isEmpty()){
            for (TopicoEntity t : professorEntity.getTopicos())
                p.getTopicos().add(topicoMapper.toSimpleTopicoDTO(t));
        }

        return p;
    }

    public SimpleProfessorDTO toSimpleProfessorDTO(ProfessorEntity professorEntity){
        SimpleProfessorDTO p = new SimpleProfessorDTO();
        p.setId(professorEntity.getId());
        p.setNome(professorEntity.getNome());
        p.setMatricula(professorEntity.getMatricula());
        p.setBiografia(professorEntity.getBiografia());
        p.setDataCriacao(professorEntity.getDataCriacao());
        p.setFormacao(professorEntity.getFormacao());
        return p;
    }


    public void updateProfessorEntityFromDTO(ProfessorInputDTO professorDeitails, ProfessorEntity professorEntity){
        modelMapper.map(professorDeitails, professorEntity);

    }


}
