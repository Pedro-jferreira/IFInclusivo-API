package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.MessageMapper;
import com.example.IfGoiano.IfCoders.entity.MessageEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.MessageRepository;

import com.example.IfGoiano.IfCoders.service.MessageService;
import com.example.IfGoiano.IfCoders.utils.UsuarioChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
//    @Autowired
//    private MessageRepository messageRepository;
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    public void sendMessage(Long sender, Long receiver, String text) {
//        // Cria uma nova mensagem
//
//    }
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UsuarioChat usuarioChat;


    @Override
    public List<MessageOutputDTO> findAll() {
        return messageRepository.findAll().stream().map(messageMapper::toMessageOutputDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MessageOutputDTO findById(Long id){
        Optional<MessageEntity> message = messageRepository.findById(id);
        if (message.isPresent()) return messageMapper.toMessageOutputDTO(message.get());
        else throw new ResourceNotFoundException("Message not found");
    }

    @Override
    @Transactional
    public MessageOutputDTO save(Long idUserEnvia, Long idUserRecebe, MessageInputDTO message){
        var userEnvia = usuarioChat.findUsuarioById(idUserEnvia);
        var userRecebe = usuarioChat.findUsuarioById(idUserRecebe);
        MessageEntity messageEntity = messageMapper.toMessageEntity(message);
        messageEntity.setUserEnvia(userEnvia);
        messageEntity.setUserRecebe(userRecebe);

        return findById(messageRepository.save(messageEntity).getId()) ;
    }

    @Override
    @Transactional
    public MessageOutputDTO update(Long id, MessageInputDTO messageDetails) {
        Optional<MessageEntity> messageOpt = messageRepository.findById(id);
        if (messageOpt.isPresent()) {
            MessageEntity messageEntity = messageOpt.get();
            messageMapper.updateMessageEntityFromDTO(messageDetails, messageEntity);
            return messageMapper.toMessageOutputDTO(messageRepository.save(messageEntity));
        }else throw new ResourceNotFoundException("Publication not found");
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        messageRepository.delete(messageMapper.toMessageEntity(findById(id)));
    }

}
