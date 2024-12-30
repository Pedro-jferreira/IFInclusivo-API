package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleLibrasDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibrasMapper {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioMapper usuarioMapper;

    public SimpleLibrasDTO toSimpleLibraDTO(LibrasEntity librasEntity){
        return modelMapper.map(librasEntity, SimpleLibrasDTO.class);
    }
    public LibrasEntity toLibrasEntity(SimpleLibrasDTO simpleLibrasDTO){
        return modelMapper.map(simpleLibrasDTO, LibrasEntity.class);
    }

    public LibrasInputDTO toLibrasInputDTO(LibrasEntity librasEntity){
        return modelMapper.map(librasEntity, LibrasInputDTO.class);
    }
    public LibrasEntity toLibrasEntity(LibrasInputDTO inputDTO){
        return modelMapper.map(inputDTO, LibrasEntity.class);
    }

    public LibrasOutputDTO toLibrasOutputDTO(LibrasEntity librasEntity){
        LibrasOutputDTO dto = new LibrasOutputDTO();
        dto.setId(librasEntity.getId());
        dto.setPalavra(librasEntity.getPalavra());
        dto.setDescricao(librasEntity.getDescricao());
        dto.setUrl(librasEntity.getUrl());
        dto.setVideo(librasEntity.getVideo());
        dto.setFoto(librasEntity.getFoto());
        dto.setJustificativa(librasEntity.getJustificativa());
        dto.setStatus(librasEntity.getStatus());

        List<SimpleUsuarioDTO> sugeriu = new ArrayList<>();
        for(int i = 0; i < librasEntity.getSugeriu().size(); i++){

            sugeriu.add(modelMapper.map(librasEntity.getSugeriu().get(i), SimpleUsuarioDTO.class));
        }
        dto.setSugeriu(sugeriu);
        dto.setCategorias(librasEntity.getCategorias());
        return dto;
    }
    public LibrasEntity toLibrasEntity(LibrasOutputDTO outputDTO){
        return modelMapper.map(outputDTO, LibrasEntity.class);
    }

    public void updateLibrasEntityFromDTO(LibrasInputDTO inputDTO, LibrasEntity librasEntity){
        modelMapper.map(inputDTO, librasEntity);
    }

}
