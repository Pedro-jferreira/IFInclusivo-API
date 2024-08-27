package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.MessageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

//    // Endpoint para enviar uma mensagem
//    @PostMapping("/send")
//    public ResponseEntity<Void> sendMessage(@RequestParam Long senderId,
//                                            @RequestParam Long receiverId,
//                                            @RequestParam String text) {
//
//        messageServiceImpl.sendMessage(senderId, receiverId, text);
//
//        return ResponseEntity.ok().build();
//    }
    @Operation(summary = "Criar uma nova Mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "message created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<MessageOutputDTO> create(@PathVariable Long idUserEnvia, @PathVariable Long idUserRecebe,@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "informações para criar uma publicação",
            required = true) @org.springframework.web.bind.annotation.RequestBody MessageInputDTO message) {
        var savedMessage = messageServiceImpl.save(idUserEnvia, idUserRecebe, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    @Operation(summary = "Buscar todas as mensagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Messages",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/messages")
    public ResponseEntity<List<MessageOutputDTO>> getAllMessages(){
        return ResponseEntity.ok(messageServiceImpl.findAll());
    }

    @Operation(summary = "Buscar Mensagens por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the message",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<MessageOutputDTO> findByIdMessage(@PathVariable Long id){
        return new ResponseEntity<>(messageServiceImpl.findById(id),HttpStatus.OK);
    }

    @Operation(summary = "Atualizar informações da mensagem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "message updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageOutputDTO> updateMessage(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "informações para atualizar uma mensagem",
            required = true) @org.springframework.web.bind.annotation.RequestBody MessageInputDTO messageDetails) {
        var message = messageServiceImpl.update(id, messageDetails);
        return ResponseEntity.ok().body(messageServiceImpl.update(id, messageDetails));
    }

    @Operation(summary = "Excluir uma mensagem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "message deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/delete")
    public ResponseEntity<MessageOutputDTO> deleteMessage(Long id){
        messageServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}