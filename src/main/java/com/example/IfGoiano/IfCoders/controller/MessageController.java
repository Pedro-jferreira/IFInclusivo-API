package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<MessageOutputDTO> create(
            @RequestBody MessageInputDTO message) {
        log.info("Chegou no controller com DTO: {}", message);
        var savedMessageDTO = messageService.save(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDTO);
    }

    @GetMapping("/conversation/{user1}/{user2}")
    public ResponseEntity<List<MessageOutputDTO>> getConversation(@PathVariable Long user1, @PathVariable Long user2) {
        return ResponseEntity.ok(messageService.getConversation(user1, user2));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<SimpleUsuarioDTO>> getUsersWhoChattedWith(@PathVariable Long userId) {
        return ResponseEntity.ok(messageService.getUsersWhoChattedWith(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
