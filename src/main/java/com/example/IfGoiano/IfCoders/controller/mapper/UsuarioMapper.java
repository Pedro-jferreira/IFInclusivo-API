package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    @Autowired
    ModelMapper modelMapper;

    public SimpleUsuarioDTO toSimpleUsuarioDTO(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, SimpleUsuarioDTO.class);
    }
    public UsuarioEntity toUsuarioEntity(SimpleUsuarioDTO simpleUsuarioDTO) {
        return modelMapper.map(simpleUsuarioDTO, UsuarioEntity.class);
    }

    public UsuarioInputDTO toUsuarioInputDTO(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioInputDTO.class);
    }

    public UsuarioEntity toUsuarioEntity(UsuarioInputDTO usuarioInputDTO) {
        return modelMapper.map(usuarioInputDTO, UsuarioEntity.class);
    }

    public UsuarioOutputDTO toUsuarioOutputDTO(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioOutputDTO.class);
    }
    public UsuarioEntity toUsuarioEntity(UsuarioOutputDTO usuarioOutputDTO) {
        return modelMapper.map(usuarioOutputDTO, UsuarioEntity.class);
    }

    public void updateUsuarioEntityFromDTO(UsuarioInputDTO usuarioDeitails, UsuarioEntity usuarioEntity){
        modelMapper.map(usuarioDeitails, usuarioEntity);

    }
}
