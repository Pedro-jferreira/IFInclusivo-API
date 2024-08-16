package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.Exception.*;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.PK.ComentarioId;
import com.example.IfGoiano.IfCoders.service.ComentarioService;
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
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Operation(summary = "Buscar todos os comentarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Comments",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<Comentario>> findAll() {

            List<Comentario> comentarios = comentarioService.findAll();
            return ResponseEntity.ok().body(comentarios);
    }

    @Operation(summary = "bscar comentario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable ComentarioId id) {

            Comentario comentario = comentarioService.findById(id);
            return ResponseEntity.ok().body(comentario);

    }

    @Operation(summary = "Criar um novo comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Comentario> create(@RequestBody Comentario comentario) {
            Comentario savedComentario = comentarioService.save(comentario);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedComentario.getId()).toUri();
            return ResponseEntity.created(location).body(savedComentario);
    }

    @Operation(summary = "Atualizar um comentário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> update(@PathVariable ComentarioId id, @RequestBody Comentario comentarioDetails) {
            return ResponseEntity.ok().body(comentarioService.update(id, comentarioDetails));
    }

    @Operation(summary = "Excluir um comentário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ComentarioId id, @RequestHeader("Authorization") String authToken) {
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}