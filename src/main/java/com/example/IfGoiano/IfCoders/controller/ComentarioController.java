package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.service.ComentarioService;
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
@RequestMapping("/comentarios")
@Tag(name = "Comentário")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @Operation(summary = "Buscar todos os comentarios", tags = "Comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all comments",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<ComentarioOutputDTO>> findAll() {
        var comentarios = service.findAll();
        return ResponseEntity.ok().body(comentarios);
    }

    @Operation(summary = "Buscar comentario por ID", tags = "Comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioOutputDTO> findById(@PathVariable Long id) {
        var comentario = service.findById(id);
        return ResponseEntity.ok().body(comentario);
    }

    @Operation(summary = "Criar um novo comentário", tags = "Comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/{idUser}/{idPublicacao}")
    public ResponseEntity<ComentarioOutputDTO> save(@PathVariable Long idUser,@PathVariable Long idPublicacao, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do comentário a ser cadastrado", required = true,
            content = @Content(schema = @Schema(implementation = ComentarioInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody ComentarioInputDTO comentario) {
            var savedComentarioDTO = service.save(idUser,idPublicacao,comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComentarioDTO);
    }

    @Operation(summary = "Atualizar um comentário por ID", tags = "Comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComentarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do comentario a ser atualizado",
            required = true, content = @Content(schema = @Schema(implementation = ComentarioInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody ComentarioInputDTO comentarioDetails) {
        var comentario = service.update(id, comentarioDetails);
        return ResponseEntity.ok().body(comentario);
    }

    @Operation(summary = "Excluir um comentário por ID", tags = "Comentário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}