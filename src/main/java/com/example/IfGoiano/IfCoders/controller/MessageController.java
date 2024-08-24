package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    // Endpoint para enviar uma mensagem
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestParam Long senderId,
                                            @RequestParam Long receiverId,
                                            @RequestParam String text) {

        messageServiceImpl.sendMessage(senderId, receiverId, text);

        return ResponseEntity.ok().build();
    }
}