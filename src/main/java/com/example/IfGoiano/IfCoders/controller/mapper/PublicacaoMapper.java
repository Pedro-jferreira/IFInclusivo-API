package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PublicacaoMapper {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Lazy
    @Autowired
    TopicoMapper topicoMapper;
    @Autowired
    ComentarioMapper comentarioMapper;


    public SimplePublicacaoDTO toSimplePublicacaoDTO(PublicacaoEntity publicacao){
        SimplePublicacaoDTO dto = new SimplePublicacaoDTO();
        dto.setId(publicacao.getId());
        dto.setTitulo(publicacao.getTitulo());
        dto.setText(publicacao.getText());
        dto.setDataCriacao(publicacao.getDataCriacao());
        dto.setUrlFoto(publicacao.getUrlFoto());
        dto.setUrlVideo(publicacao.getUrlVideo());
        dto.setUsuario(usuarioMapper.toSimpleDTO(publicacao.getUsuario()));
        return dto;
    }
    public PublicacaoEntity toPublicacaoEntity(SimplePublicacaoDTO simplePublicacaoDTO){
        return modelMapper.map(simplePublicacaoDTO, PublicacaoEntity.class);
    }


    public PublicacaoInputDTO toPublicacaoInputDTO(PublicacaoEntity publicacaoEntity){
        return modelMapper.map(publicacaoEntity, PublicacaoInputDTO.class);
    }
    public PublicacaoEntity toPublicacaoEntity(PublicacaoInputDTO inputDTO){
        PublicacaoEntity entity = modelMapper.map(inputDTO, PublicacaoEntity.class);
        return entity;
    }


    public PublicacaoOutputDTO toPublicacaoOutputDTO(PublicacaoEntity publicacaoEntity){
        PublicacaoOutputDTO dto = new PublicacaoOutputDTO();
        dto.setId(publicacaoEntity.getId());
        dto.setTitulo(publicacaoEntity.getTitulo());
        dto.setText(publicacaoEntity.getText());
        dto.setDataCriacao(publicacaoEntity.getDataCriacao());
        dto.setUrlFoto(publicacaoEntity.getUrlFoto());
        dto.setUrlVideo(publicacaoEntity.getUrlVideo());
        dto.setUsuario(usuarioMapper.toSimpleDTO(publicacaoEntity.getUsuario()));
        if (!publicacaoEntity.getTopicoEntities().isEmpty()){
            for (TopicoEntity topicoEntity : publicacaoEntity.getTopicoEntities()){
                dto.getTopicos().add(topicoMapper.toSimpleTopicoDTO(topicoEntity));
            }
        }
        if (!publicacaoEntity.getComentarios().isEmpty()){
            for (ComentarioEntity comentarioEntity : publicacaoEntity.getComentarios()){
                dto.getComentarios().add(comentarioMapper.toSimpleComentarioDTO(comentarioEntity));
            }
        }
        if (!publicacaoEntity.getLikeBy().isEmpty()){
            for (UsuarioEntity likeByEntity : publicacaoEntity.getLikeBy()){
                dto.getLikeBy().add(usuarioMapper.toSimpleDTO(likeByEntity));
            }
        }

        return dto;
    }
    public PublicacaoEntity toPublicacaoEntity(PublicacaoOutputDTO inputDTO) {
        return modelMapper.map(inputDTO, PublicacaoEntity.class);
    }


    public void updatePublicacaoEntityFromDTO(PublicacaoInputDTO publicacaoDeitails, PublicacaoEntity publicacaoEntity){
        modelMapper.map(publicacaoDeitails, publicacaoEntity);
    }


}
