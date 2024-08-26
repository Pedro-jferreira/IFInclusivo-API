package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SimpleComentarioDTO toSimpleComentarioDTO(ComentarioEntity comentarioEntity) {
        return modelMapper.map(comentarioEntity, SimpleComentarioDTO.class);
    }
    public ComentarioEntity toComentarioEntity(SimpleComentarioDTO simpleComentarioDTO) {
        return modelMapper.map(simpleComentarioDTO, ComentarioEntity.class);
    }


    public ComentarioInputDTO toComentarioInputDTO(ComentarioEntity comentarioEntity) {
        return modelMapper.map(comentarioEntity, ComentarioInputDTO.class);
    }
    public ComentarioEntity toComentarioEntity(ComentarioInputDTO comentarioInputDTO) {
        return modelMapper.map(comentarioInputDTO, ComentarioEntity.class);
    }


    public ComentarioOutputDTO toComentarioOutputDTO(ComentarioEntity comentarioEntity) {
        return modelMapper.map(comentarioEntity, ComentarioOutputDTO.class);
    }
    public ComentarioEntity toComentarioEntity(ComentarioOutputDTO comentarioOutputDTO) {
        return modelMapper.map(comentarioOutputDTO, ComentarioEntity.class);
    }



    public void updateComentarioEntityFromDTO(ComentarioInputDTO comentarioDeitals, ComentarioEntity comentarioEntity) {
        modelMapper.map(comentarioDeitals, comentarioEntity);
    }

}