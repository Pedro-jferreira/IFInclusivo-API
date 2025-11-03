package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasOutputDTOV2;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleInterpreteDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleLibrasDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTOCreated;
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
        LibrasInputDTO dto = new LibrasInputDTO();
        dto.setPalavra(librasEntity.getPalavra());
        dto.setDescricao(librasEntity.getDescricao());
        dto.setUrl(librasEntity.getUrl());
        return dto;
    }
    public LibrasEntity toLibrasEntity(LibrasInputDTO inputDTO){
        LibrasEntity entity = new LibrasEntity();
        entity.setPalavra(inputDTO.getPalavra());
        entity.setDescricao(inputDTO.getDescricao());
        entity.setUrl(inputDTO.getUrl());
        return entity;
    }

    public LibrasOutputDTO toLibrasOutputDTO(LibrasEntity librasEntity){
        LibrasOutputDTO dto = new LibrasOutputDTO();
        dto.setId(librasEntity.getId());
        dto.setPalavra(librasEntity.getPalavra());
        dto.setDescricao(librasEntity.getDescricao());
        dto.setUrl(librasEntity.getUrl());
        dto.setFileUrl(librasEntity.getFileUrl());
        dto.setJustificativa(librasEntity.getJustificativa());
        dto.setStatus(librasEntity.getStatus());

        List<SimpleUsuarioDTO> sugeriu = new ArrayList<>();
        for(int i = 0; i < librasEntity.getSugeriu().size(); i++){
            sugeriu.add(modelMapper.map(librasEntity.getSugeriu().get(i), SimpleUsuarioDTO.class));
        }

        List<SimpleInterpreteDTO> intepreteAnalise = new ArrayList<>();
        for(int i = 0; i< librasEntity.getInterprete().size(); i++){
          intepreteAnalise.add(modelMapper.map(librasEntity.getInterprete().get(i), SimpleInterpreteDTO.class));
        }
        dto.setSugeriu(sugeriu);
        dto.setInterprete(intepreteAnalise);
        dto.setCategorias(librasEntity.getCategorias());
        return dto;
    }
    public LibrasEntity toLibrasEntity(LibrasOutputDTO outputDTO){
        return modelMapper.map(outputDTO, LibrasEntity.class);
    }

    public void updateLibrasEntityFromDTO(LibrasInputDTO inputDTO, LibrasEntity librasEntity){
        librasEntity.setPalavra(inputDTO.getPalavra());
        librasEntity.setDescricao(inputDTO.getDescricao());
        librasEntity.setUrl(inputDTO.getUrl());
    }

    public LibrasEntity toLibrasEntity(LibrasInputDTOCreated inputDTO){
        LibrasEntity entity = new LibrasEntity();
        entity.setPalavra(inputDTO.getPalavra());
        entity.setDescricao(inputDTO.getDescricao());
        entity.setUrl(inputDTO.getUrl());
        entity.setJustificativa(inputDTO.getJustificativa());
        entity.setStatus(inputDTO.getStatus());
        entity.setCategorias(inputDTO.getCategorias());
        return entity;
    }


    public LibrasOutputDTOV2 toLibrasOutputDTOV2(LibrasEntity librasEntity){
        LibrasOutputDTOV2 dto = new LibrasOutputDTOV2();
        dto.setId(librasEntity.getId());
        dto.setPalavra(librasEntity.getPalavra());
        dto.setDescricao(librasEntity.getDescricao());
        dto.setUrl(librasEntity.getUrl());
        dto.setFileUrl(librasEntity.getFileUrl());
        dto.setJustificativa(librasEntity.getJustificativa());
        dto.setStatus(librasEntity.getStatus());
        dto.setCategorias(librasEntity.getCategorias());
        return dto;
    }

}
