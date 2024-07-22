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
        try {
            List<Comentario> comentarios = comentarioService.findAll();
            return ResponseEntity.ok().body(comentarios);
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while fetching comentarios", e);
        }
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
        try {
            Comentario comentario = comentarioService.findById(id);

            if (comentario == null) throw new ResourceNotFoundException(id);

            return ResponseEntity.ok().body(comentario);
        }catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching comment with ID: " + id, e);
        }
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
        try {
            if (comentario == null || comentario.getContent().isEmpty()) {
                throw new BadRequestException("Comentario cannot be null or empty");
            }
            Comentario savedComentario = comentarioService.save(comentario);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedComentario.getId()).toUri();
            return ResponseEntity.created(location).body(savedComentario);
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while saving the comentario", e);
        }
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
        try {
            if (comentarioService.findById(id) == null)
                throw new ResourceNotFoundException(id);

            if (comentarioDetails == null || comentarioDetails.getContent().isEmpty())
                throw new BadRequestException("comment details cannot be null or empty");

            return ResponseEntity.ok().body(comentarioService.update(id, comentarioDetails));

        } catch (DataBaseException e) {
                throw new InternalServerErrorException("Database error occurred while updating the comment", e);
            }
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
        try {
            Comentario comentario = comentarioService.findById(id);
            if (comentario == null) throw new ResourceNotFoundException(id);

            if (!isUserAuthorizedToDelete(authToken, comentario))
                throw new UnauthorizedException("You are not authorized to delete this comment");

        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while deleting the comment", e);
        }
    }

    private boolean isUserAuthorizedToDelete(String authToken, Comentario comentario) {
        // Lógica para verificar se o usuário está autorizado a deletar o comentário
        return true;
    }

}