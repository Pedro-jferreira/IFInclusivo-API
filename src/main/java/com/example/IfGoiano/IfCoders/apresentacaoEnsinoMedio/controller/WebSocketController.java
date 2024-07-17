package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) {
        return message;
    }
}
