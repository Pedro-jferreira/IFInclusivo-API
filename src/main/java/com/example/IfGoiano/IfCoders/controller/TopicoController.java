package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.TopicoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoServiceImpl topicoServiceImpl;


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
            return ResponseEntity.ok().body(topicoServiceImpl.findAll());
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
        TopicoOutputDTO topico = topicoServiceImpl.findById(id);
            return ResponseEntity.ok().body(topico);
    }

    @Operation(summary = "Criar um novo Topico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "topic created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicoOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<TopicoOutputDTO> create(@RequestBody TopicoInputDTO topico) {

        TopicoOutputDTO topico1 = topicoServiceImpl.save(topico);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(topico1.getId()).toUri();
            return ResponseEntity.created(location).body(topico1);
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
    public ResponseEntity<TopicoOutputDTO> update(@PathVariable Long id, @RequestBody TopicoInputDTO topicoDetails) {
            return ResponseEntity.ok().body(topicoServiceImpl.update(id, topicoDetails));
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
            topicoServiceImpl.delete(id);
            return ResponseEntity.noContent().build();
    }



}
