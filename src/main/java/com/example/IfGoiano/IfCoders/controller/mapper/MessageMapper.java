package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleMessageDTO;
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
        if (outputDTO == null) {
            return null;
        }

        MessageEntity entity = new MessageEntity();
        entity.setId(outputDTO.getId());
        entity.setDateTime(outputDTO.getDateTime());
        entity.setVideo(outputDTO.getVideo());
        entity.setText(outputDTO.getText());

        // Mapeamento do campo 'usuario' que envia
        if (outputDTO.getUserEnvia() != null) {
            entity.setUserEnvia(usuarioChat.findUsuarioById(outputDTO.getUserEnvia().getId()));
        }

        // Mapeamento do campo 'usuario' que recebe
        if (outputDTO.getUserRecebe() != null) {
            entity.setUserRecebe(usuarioChat.findUsuarioById(outputDTO.getUserRecebe().getId()));
        }

        return entity;
    }


    public void updateMessageEntityFromDTO(MessageInputDTO messageDetails, MessageEntity messageEntity){
        modelMapper.map(messageDetails, messageEntity);
    }
}
