package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TopicoMapper {

    @Autowired
    ModelMapper modelMapper;
    @Lazy
    @Autowired
    ProfessorMapper professorMapper;

    public SimpleTopicoDTO toSimpleTopicoDTO(TopicoEntity topicoEntity) {
        SimpleTopicoDTO simpleTopicoDTO = new SimpleTopicoDTO();
        simpleTopicoDTO.setId(topicoEntity.getId());
        simpleTopicoDTO.setTitulo(topicoEntity.getTitulo());
        simpleTopicoDTO.setDescricao(topicoEntity.getDescricao());
        simpleTopicoDTO.setCategoria(topicoEntity.getCategoria());
        simpleTopicoDTO.setDataCriacao(topicoEntity.getDataCriacao());
        simpleTopicoDTO.setProfessor(professorMapper.
                toSimpleProfessorDTO(topicoEntity.getProfessor()));
        return modelMapper.map(topicoEntity, SimpleTopicoDTO.class);
    }




    private Long id;
    private String titulo;
    private String descricao;
    private Categorias categoria;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleProfessorDTO professor;
    public TopicoEntity toTopicoEntity(SimpleTopicoDTO simpleTopicoDTO) {
        return modelMapper.map(simpleTopicoDTO, TopicoEntity.class);
    }

    public TopicoInputDTO toTopicoInputDTO(TopicoEntity topicoEntity) {
        return modelMapper.map(topicoEntity, TopicoInputDTO.class);
    }
    public TopicoEntity toTopicoEntity(TopicoInputDTO inputDTO) {
        return modelMapper.map(inputDTO, TopicoEntity.class);
    }

    public TopicoOutputDTO toTopicoOutputDTO(TopicoEntity topicoEntity) {
        return modelMapper.map(topicoEntity, TopicoOutputDTO.class);
    }
    public TopicoEntity toTopicoEntity(TopicoOutputDTO inputDTO) {
        return modelMapper.map(inputDTO, TopicoEntity.class);
    }

    public void updateTopicoEntityFromDTO(TopicoInputDTO topicoDeitailsDTO, TopicoEntity topicoEntity) {
        modelMapper.map(topicoDeitailsDTO, topicoEntity);
    }

}