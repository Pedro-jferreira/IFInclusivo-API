package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.DTO.ComentarioDTO;
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
                            schema = @Schema(implementation = ComentarioDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> findAll() {

            List<ComentarioDTO> comentarios = comentarioService.findAll();
            return ResponseEntity.ok().body(comentarios);
    }

    @Operation(summary = "bscar comentario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> findById(@PathVariable Long id) {

            ComentarioDTO comentario = comentarioService.findById(id);
            return ResponseEntity.ok().body(comentario);

    }

    @Operation(summary = "Criar um novo comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ComentarioDTO> create(@RequestBody ComentarioDTO comentario) {
            ComentarioDTO savedComentarioDTO = comentarioService.save(comentario);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(ComentarioDTO.class).toUri();
            return ResponseEntity.created(location).body(savedComentarioDTO);
    }

    @Operation(summary = "Atualizar um comentário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDetails) {
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
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}