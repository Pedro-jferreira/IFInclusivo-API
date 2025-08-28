package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.MessageMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.*;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.MessageRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.MessageService;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private UsuarioRepository usuario;
    @Autowired
    UsuarioMapper usuarioMapper;
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Transactional
    @Override
    public MessageOutputDTO save(MessageInputDTO message) {
        var userEnvia = usuario.findById(message.getIdUserEnvia())
                .orElseThrow(() -> new IllegalArgumentException("Usuário remetente não encontrado"));
        log.info("Usuário remetente encontrado: id={}, nome={}", userEnvia.getId(), userEnvia.getNome());

        var userRecebe = usuario.findById(message.getIdUserRecebe())
                .orElseThrow(() -> new IllegalArgumentException("Usuário destinatário não encontrado"));

        log.info("Usuário destinatário encontrado: id={}, nome={}", userRecebe.getId(), userRecebe.getNome());


        if (isValidUserType(userEnvia) && isValidUserType(userRecebe)) {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setUserEnvia(userEnvia);
            messageEntity.setUserRecebe(userRecebe);
            messageEntity.setText(message.getText());
            messageEntity.setVisualizado(false);

            return findById(messageRepository.save(messageEntity).getId());
        } else {
            throw new IllegalArgumentException("Um ou mais usuários não têm um tipo válido para envio de mensagens.");
        }
    }

    @Transactional
    @Override
    public MessageOutputDTO findById(Long id){
        var message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message not found"));
        return toMessageOutputDTO(message);
    }

    public MessageOutputDTO toMessageOutputDTO(MessageEntity messageEntity){
        MessageOutputDTO dto = new MessageOutputDTO();
        dto.setId(messageEntity.getId());
        dto.setDataCriacao(messageEntity.getDataCriacao());
        dto.setText(messageEntity.getText());
        dto.setVisualizado(messageEntity.getVisualizado());

        dto.setUserEnvia(toSimpleUsuarioDTO(messageEntity.getUserEnvia()));
        dto.setUserRecebe(toSimpleUsuarioDTO(messageEntity.getUserRecebe()));

        return dto;
    }


    private SimpleUsuarioDTO toSimpleUsuarioDTO(UsuarioEntity user) {
        SimpleUsuarioDTO dto = new SimpleUsuarioDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setBiografia(user.getBiografia());
        dto.setMatricula(user.getMatricula());
        dto.setRoles(user.getRoles());
        dto.setDataCriacao(user.getDataCriacao());
        return dto;
    }
    private boolean isValidUserType(Object user) {
        return user instanceof AlunoNapneEntity ||
                user instanceof ProfessorEntity ||
                user instanceof TutorEntity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageOutputDTO> getConversation(Long user1, Long user2) {
        return messageRepository.getConversation(user1, user2)
                .stream().map(this::toMessageOutputDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<SimpleUsuarioDTO> getUsersWhoChattedWith(Long userId) {

      var result =   messageRepository.getIdsOfUsersWhoChattedWith(userId);
      List<SimpleUsuarioDTO> dtos = new ArrayList<>();
      for (Long id : result) {
          UsuarioEntity usuarioEntity = usuario.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
          dtos.add(toSimpleUsuarioDTO(usuarioEntity));
      }
         return dtos;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MessageEntity message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));

        // só pode excluir se tiver até 30 minutos
        if (message.getDataCriacao().isBefore(LocalDateTime.now().minusMinutes(30))) {
            throw new IllegalStateException("Mensagem só pode ser excluída até 30 minutos após o envio.");
        }

        messageRepository.delete(message);
    }
}


