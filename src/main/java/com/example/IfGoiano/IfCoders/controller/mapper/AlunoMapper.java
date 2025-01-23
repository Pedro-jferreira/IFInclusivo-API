package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConfigAcblMapper configAcblMapper;
    @Lazy
    @Autowired
    private CursoMapper cursoMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;

    // Métodos de mapeamento
    public SimpleAlunoDTO toSimpleAlunoDTO(AlunoEntity alunoEntity) {
        SimpleAlunoDTO  dto = new SimpleAlunoDTO();
        dto.setId(alunoEntity.getId());
        dto.setNome(alunoEntity.getNome());
        dto.setMatricula(alunoEntity.getMatricula());
        dto.setBiografia(alunoEntity.getBiografia());
        dto.setDataCriacao(alunoEntity.getDataCriacao());
        dto.setCurso(cursoMapper.toSimpleCursoDTO(alunoEntity.getCurso()));
        return dto;
    }

    public AlunoEntity toAlunoEntity(SimpleAlunoDTO simpleAlunoDTO) {
        AlunoEntity entity = new AlunoEntity();
        entity.setId(simpleAlunoDTO.getId());
        entity.setNome(simpleAlunoDTO.getNome());
        entity.setMatricula(simpleAlunoDTO.getMatricula());
        entity.setBiografia(simpleAlunoDTO.getBiografia());
        entity.setDataCriacao(simpleAlunoDTO.getDataCriacao());
        entity.setCurso(cursoMapper.toCursoEntity(simpleAlunoDTO.getCurso()));
        return entity;
    }


    public AlunoInputDTO toAlunoInputDTO(AlunoEntity alunoEntity) {
        return modelMapper.map(alunoEntity, AlunoInputDTO.class);
    }

    public AlunoEntity toAlunoEntity(AlunoInputDTO alunoInputDTO) {
        AlunoEntity entity = new AlunoEntity();
        entity.setNome(alunoInputDTO.getNome());
        entity.setLogin(alunoInputDTO.getLogin());
        entity.setSenha(alunoInputDTO.getSenha());
        entity.setMatricula(alunoInputDTO.getMatricula());
        entity.setBiografia(alunoInputDTO.getBiografia());
        return entity;
    }


    public AlunoOutputDTO toAlunoOutputDTO(AlunoEntity alunoEntity) {
        AlunoOutputDTO dto = new AlunoOutputDTO();
        dto.setId(alunoEntity.getId());
        dto.setNome(alunoEntity.getNome());
        dto.setLogin(alunoEntity.getLogin());
        dto.setSenha(alunoEntity.getSenha());
        dto.setMatricula(alunoEntity.getMatricula());
        dto.setBiografia(alunoEntity.getBiografia());
        dto.setDataCriacao(alunoEntity.getDataCriacao());
        if (alunoEntity.getConfigAcessibilidadeEntity()!= null){
            dto.setConfigAcessibilidadeEntity(configAcblMapper.toConfigAcblOutputDTO(alunoEntity.getConfigAcessibilidadeEntity()));
        }
        dto.setCurso(cursoMapper.toSimpleCursoDTO(alunoEntity.getCurso()));
        return dto;
    }
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private ConfigAcblOutputDTO configAcessibilidadeEntity;

    public AlunoEntity toAlunoEntity(AlunoOutputDTO alunoOutputDTO) {
        return modelMapper.map(alunoOutputDTO, AlunoEntity.class);
    }

    public void updateAlunoEntityFromDTO(AlunoInputDTO alunoDetails, AlunoEntity alunoEntity) {
        modelMapper.map(alunoDetails, alunoEntity);
    }
}


