package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicacaoMapper {
    @Autowired
    ModelMapper modelMapper;

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
        System.out.println("PublicacaoOutputDTO: " + inputDTO);
        PublicacaoEntity entity= modelMapper.map(inputDTO, PublicacaoEntity.class);
        System.out.println("Mapped PublicacaoEntity: " + entity);
        return entity;
    }


    public void updatePublicacaoEntityFromDTO(PublicacaoInputDTO publicacaoDeitails, PublicacaoEntity publicacaoEntity){
        modelMapper.map(publicacaoDeitails, publicacaoEntity);
    }


}
