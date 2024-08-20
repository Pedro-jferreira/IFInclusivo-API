package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.MessageEntity;
import com.example.IfGoiano.IfCoders.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;



    public void sendMessage(Long sender, Long receiver, String text) {
        // Cria uma nova mensagem

    }

}
