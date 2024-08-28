package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleMessageDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.entity.*;
import com.example.IfGoiano.IfCoders.utils.UsuarioChat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioChat usuarioChat;

    public SimpleMessageDTO toSimpleMessageDTO(MessageEntity messageEntity){
        return modelMapper.map(messageEntity, SimpleMessageDTO.class);
    }
    public MessageEntity toMessageEntity(SimpleMessageDTO simpleMessageDTO){
        return modelMapper.map(simpleMessageDTO, MessageEntity.class);
    }


    public MessageInputDTO toMessageInputDTO(MessageEntity messageEntity){
        return modelMapper.map(messageEntity, MessageInputDTO.class);
    }
    public MessageEntity toMessageEntity(MessageInputDTO inputDTO){
        MessageEntity entity = modelMapper.map(inputDTO, MessageEntity.class);
        return entity;
    }


    public MessageOutputDTO toMessageOutputDTO(MessageEntity messageEntity){
        MessageOutputDTO entity = modelMapper.map(messageEntity, MessageOutputDTO.class);
        return entity;
    }
    public MessageEntity toMessageEntity(MessageOutputDTO outputDTO){
        MessageEntity entity = modelMapper.map(outputDTO, MessageEntity.class);
        return entity;
    }


    public void updateMessageEntityFromDTO(MessageInputDTO messageDetails, MessageEntity messageEntity){
        System.out.println("entrou aqui 4");
        modelMapper.map(messageDetails, messageEntity);
    }
}
