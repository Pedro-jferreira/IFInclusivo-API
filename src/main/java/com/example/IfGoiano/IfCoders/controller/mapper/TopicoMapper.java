package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TopicoMapper {

    @Autowired
    ModelMapper modelMapper;
    @Lazy
    @Autowired
    ProfessorMapper professorMapper;
    @Lazy
    @Autowired
    PublicacaoMapper publicacaoMapper;

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
        TopicoOutputDTO output = new TopicoOutputDTO();
        output.setId(topicoEntity.getId());
        output.setTitulo(topicoEntity.getTitulo());
        output.setDescricao(topicoEntity.getDescricao());
        output.setCategoria(topicoEntity.getCategoria());
        output.setDataCriacao(topicoEntity.getDataCriacao());
        output.setProfessor(professorMapper.toSimpleProfessorDTO(topicoEntity.getProfessor()));
        if (!topicoEntity.getPublicacaoEntities().isEmpty()){
            for (PublicacaoEntity publicacaoEntity : topicoEntity.getPublicacaoEntities()) {
                output.getPublicacoes().add(publicacaoMapper.toSimplePublicacaoDTO(publicacaoEntity));
            }
        }


        return output;
    }
    public TopicoEntity toTopicoEntity(TopicoOutputDTO inputDTO) {
        return modelMapper.map(inputDTO, TopicoEntity.class);
    }

    public void updateTopicoEntityFromDTO(TopicoInputDTO topicoDeitailsDTO, TopicoEntity topicoEntity) {
        modelMapper.map(topicoDeitailsDTO, topicoEntity);
    }

}