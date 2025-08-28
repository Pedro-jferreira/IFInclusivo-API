package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(MessageInputDTO messageInput) {
        MessageOutputDTO savedMessage =
                messageService.save(messageInput);

        // envia para o destinatário
        simpMessagingTemplate.convertAndSend(
                "/topic/messages/" + messageInput.getIdUserRecebe(),
                savedMessage
        );

        // também envia para o remetente (para atualização instantânea)
        simpMessagingTemplate.convertAndSend(
                "/topic/messages/" + messageInput.getIdUserEnvia(),
                savedMessage
        );
    }
}
