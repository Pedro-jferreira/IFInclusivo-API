package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Operation(summary = "Criar um novo Topico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "topic created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicoOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<TopicoOutputDTO> create(@RequestBody(description = "informações para criar um Tópico",
                    required = true) @org.springframework.web.bind.annotation.RequestBody TopicoInputDTO topico) {
        var topico1 = service.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico1);
    }

    @Operation(summary = "Atualizar um Topico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "topic updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "topic not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<TopicoOutputDTO>
    update(@PathVariable Long id, @RequestBody(description = "informações para atualizar um tópico",
                    required = true) @org.springframework.web.bind.annotation.RequestBody TopicoInputDTO topicoDetails) {
        return ResponseEntity.ok().body(service.update(id,  topicoDetails));
    }

    @Operation(summary = "Excluir um Topico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "topic deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "topic not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar Topico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the topic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "topic not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<TopicoOutputDTO> findById(@PathVariable Long id) {
        TopicoOutputDTO topico = service.findById(id);
        return ResponseEntity.ok().body(topico);
    }

    @Operation(summary = "Buscar todos os Topicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all topic",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicoOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TopicoOutputDTO>> findAll() {
            return ResponseEntity.ok().body(service.findAll());
    }
}