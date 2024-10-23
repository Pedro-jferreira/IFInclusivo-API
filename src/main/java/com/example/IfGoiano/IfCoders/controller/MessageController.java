package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@Tag(name = "Mensagem")
public class MessageController {

    @Autowired
    private MessageService messageService;

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
    @PostMapping("/create/{idUserEnvia}/{idUserRecebe}")
    public ResponseEntity<MessageOutputDTO> create(@PathVariable Long idUserEnvia, @PathVariable Long idUserRecebe,@RequestBody(description = "dados para criar uma mensagem",
            required = true, content = @Content(schema = @Schema(implementation = MessageInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody MessageInputDTO message) {
        var savedMessageDTO = messageService.save(idUserEnvia, idUserRecebe, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDTO);
    }

    @Operation(summary = "Buscar todas as mensagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Messages",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<MessageOutputDTO>> getAllMessages(){
        var messages = messageService.findAll();
        return ResponseEntity.ok().body(messages);
    }

    @Operation(summary = "Buscar Mensagens por ID", tags = "Mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the message",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MessageOutputDTO> findById(@PathVariable Long id){
        var message = messageService.findById(id);
        return ResponseEntity.ok().body(message);
    }

    @Operation(summary = "Criar uma nova mensagem", tags = "Mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/create/{idUserEnvia}/{idUserRecebe}")
    public ResponseEntity<MessageOutputDTO> save(@PathVariable Long idUserEnvia, @PathVariable Long idUserRecebe,@RequestBody(description = "dados para criar uma mensagem",
            required = true, content = @Content(schema = @Schema(implementation = MessageInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody MessageInputDTO message) {
        var savedMessageDTO = messageService.save(idUserEnvia, idUserRecebe, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDTO);
    }

    @Operation(summary = "Atualizar informações da mensagem por ID", tags = "Mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageOutputDTO> updateMessage(@PathVariable Long id, @RequestBody(description = "dados para atualizar uma mensagem",
            required = true) @org.springframework.web.bind.annotation.RequestBody MessageInputDTO messageDetails) {
        var message = messageService.update(id, messageDetails);
        return ResponseEntity.ok().body(message);
    }

    @Operation(summary = "Excluir uma mensagem por ID", tags = "Mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Message deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Message not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/delete")
    public ResponseEntity<MessageOutputDTO> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}