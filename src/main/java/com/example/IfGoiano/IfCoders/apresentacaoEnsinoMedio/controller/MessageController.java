package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.controller;

import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.entity.MessageEntity;
import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity entity) {
        MessageEntity createdMessage = messageService.createMessage(entity);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<MessageEntity>> findAllMessages() {
        List<MessageEntity> messages = messageService.findAllMessagem();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable Long id) {
        MessageEntity message = messageService.getById(id);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/sent{idE}/received{idR}")
    public ResponseEntity<List<MessageEntity>> getExchangedMessages(@PathVariable Long idE,@PathVariable Long idR){
        return ResponseEntity.ok(messageService.getExchangedMessages(idE,idR));
    }


}