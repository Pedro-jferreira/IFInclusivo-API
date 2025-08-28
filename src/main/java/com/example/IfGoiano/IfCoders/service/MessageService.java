package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageService {


    @Transactional
    MessageOutputDTO save(MessageInputDTO message);

    @Transactional
    MessageOutputDTO findById(Long id);

    @Transactional(readOnly = true)
    List<MessageOutputDTO> getConversation(Long user1, Long user2);

    @Transactional(readOnly = true)
    List<SimpleUsuarioDTO> getUsersWhoChattedWith(Long userId);

    void delete(Long id);
}
