package com.example.IfGoiano.IfCoders.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
@SecurityRequirement(name = "bearerAuth")
@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) {
        return message;
    }
}
