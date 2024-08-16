package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.Exception.*;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Topico;
import com.example.IfGoiano.IfCoders.service.TopicoService;
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
    private TopicoService topicoService;


    @Operation(summary = "Buscar todos os Topicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all topic",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Publicacao.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Topico>> findAll() {
            return ResponseEntity.ok().body(topicoService.findAll());
    }

    @Operation(summary = "Buscar Topico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the topic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "topic not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Topico> findById(@PathVariable Long id) {
            Topico topico = topicoService.findById(id);
            return ResponseEntity.ok().body(topico);
    }

    @Operation(summary = "Criar um novo Topico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "topic created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Topico> create(@RequestBody Topico topico) {

            Topico topico1 = topicoService.save(topico);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(topico1.getId()).toUri();
            return ResponseEntity.created(location).body(topico1);
    }

    @Operation(summary = "Atualizar um Topico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "topic updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "topic not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Topico> update(@PathVariable Long id, @RequestBody Topico topicoDetails) {
            return ResponseEntity.ok().body(topicoService.update(id, topicoDetails));
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
            topicoService.delete(id);
            return ResponseEntity.noContent().build();
    }



}
