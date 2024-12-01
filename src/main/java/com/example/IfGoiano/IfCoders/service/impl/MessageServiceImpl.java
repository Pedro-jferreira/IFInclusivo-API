package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.MessageMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.entity.MessageEntity;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.MessageRepository;
import com.example.IfGoiano.IfCoders.service.MessageService;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UsuarioService usuario;
    @Autowired
    UsuarioMapper usuarioMapper;


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
    public MessageOutputDTO save(Long idUserEnvia, Long idUserRecebe, MessageInputDTO message) {
        var userEnvia = usuarioMapper.toEntity(usuario.findById(idUserEnvia));
        var userRecebe = usuarioMapper.toEntity(usuario.findById(idUserRecebe));
        MessageEntity messageEntity = messageMapper.toMessageEntity(message);

        if (isValidUserType(userEnvia) && isValidUserType(userRecebe)) {
            messageEntity.setUserEnvia(userEnvia);
            messageEntity.setUserRecebe(userRecebe);
            messageEntity.setText(message.getText());
            messageEntity.setVisualizado(false);
            return findById(messageRepository.save(messageEntity).getId());
        } else {
            throw new IllegalArgumentException("Um ou mais usuários não têm um tipo válido para envio de mensagens.");
        }

    }


    private boolean isValidUserType(Object user) {
        return user instanceof AlunoNapneEntity ||
                user instanceof ProfessorEntity ||
                user instanceof TutorEntity;
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
