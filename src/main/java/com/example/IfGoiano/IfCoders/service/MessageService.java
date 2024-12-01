package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;

import java.util.List;

public interface MessageService {
    List<MessageOutputDTO> findAll();

    MessageOutputDTO findById(Long id);

    MessageOutputDTO save(Long idUserEnvia, Long idUserRecebe, MessageInputDTO message);

    MessageOutputDTO update(Long id, MessageInputDTO messageDetails);

    void delete(Long id);
}
