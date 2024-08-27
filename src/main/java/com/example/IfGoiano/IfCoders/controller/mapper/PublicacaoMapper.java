package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.utils.UsuarioFinder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublicacaoMapper {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioFinder usuarioFinder;

    public SimplePublicacaoDTO toSimplePublicacaoDTO(PublicacaoEntity publicacao){
        return modelMapper.map(publicacao, SimplePublicacaoDTO.class);
    }
    public PublicacaoEntity toPublicacaoEntity(SimplePublicacaoDTO simplePublicacaoDTO){
        return modelMapper.map(simplePublicacaoDTO, PublicacaoEntity.class);
    }


    public PublicacaoInputDTO toPublicacaoInputDTO(PublicacaoEntity publicacaoEntity){
        return modelMapper.map(publicacaoEntity, PublicacaoInputDTO.class);
    }
    public PublicacaoEntity toPublicacaoEntity(PublicacaoInputDTO inputDTO){
        System.out.println("PublicacaoInputDTO: " + inputDTO);
        PublicacaoEntity entity = modelMapper.map(inputDTO, PublicacaoEntity.class);
        System.out.println("Mapped PublicacaoEntity: " + entity);
        return entity;
    }


    public PublicacaoOutputDTO toPublicacaoOutputDTO(PublicacaoEntity publicacaoEntity){
        System.out.println("PublicacaoInputDTO: " + publicacaoEntity);
        PublicacaoOutputDTO entity =modelMapper.map(publicacaoEntity, PublicacaoOutputDTO.class);
        System.out.println("Mapped PublicacaoOutputDTO: " + entity);
        return entity;
    }
    public PublicacaoEntity toPublicacaoEntity(PublicacaoOutputDTO inputDTO){
        if (inputDTO == null) {
            return null;  // Trate casos nulos conforme necessário
        }

        PublicacaoEntity entity = new PublicacaoEntity();

        entity.setId(inputDTO.getId());  // Mapeando o ID
        entity.setText(inputDTO.getText());  // Mapeando o texto
        entity.setUrlVideo(inputDTO.getUrlVideo());  // Mapeando a URL do vídeo
        entity.setUrlFoto(inputDTO.getUrlFoto());  // Mapeando a URL da foto
        entity.setDataCriacao(inputDTO.getDataCriacao());  // Mapeando a data de criação

        // Mapeamento do campo 'usuario'
        if (inputDTO.getUsuario() != null) {
            entity.setUsuario(usuarioFinder.findUsuarioById(inputDTO.getUsuario().getId()));
        }

        // Mapeamento do campo 'topico'
        if (inputDTO.getTopico() != null) {
            TopicoEntity topicoEntity = new TopicoEntity();
            topicoEntity.setId(inputDTO.getTopico().getId());
            topicoEntity.setCategoria(inputDTO.getTopico().getCategoria());
            topicoEntity.setDescripcion(inputDTO.getTopico().getDescripcion());
            topicoEntity.setTema(inputDTO.getTopico().getTema());
            entity.setTopico(topicoEntity);
        }

        if (inputDTO.getComentarios() != null) {
            List<ComentarioEntity> comentarioEntities = new ArrayList<>();
            for (SimpleComentarioDTO comentarioDTO : inputDTO.getComentarios()) {
                ComentarioEntity comentarioEntity = new ComentarioEntity();
                comentarioEntity.setId(comentarioDTO.getId());
                comentarioEntity.setContent(comentarioDTO.getContent());
                comentarioEntities.add(comentarioEntity);
            }
            entity.setComentarios(comentarioEntities);
        }

        // Mapeamento da lista 'likeBy'
        if (inputDTO.getLikeBy() != null) {
            List<UsuarioEntity> likeByEntities = new ArrayList<>();
            for (SimpleUsuarioDTO usuarioDTO : inputDTO.getLikeBy()) {
                likeByEntities.add(usuarioFinder.findUsuarioById(usuarioDTO.getId()));
            }
            entity.setLikeBy(likeByEntities);
        }

        return entity;

    }


    public void updatePublicacaoEntityFromDTO(PublicacaoInputDTO publicacaoDeitails, PublicacaoEntity publicacaoEntity){
        modelMapper.map(publicacaoDeitails, publicacaoEntity);
    }


}
