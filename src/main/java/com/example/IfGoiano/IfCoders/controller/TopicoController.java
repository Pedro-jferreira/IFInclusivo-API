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
        try {
            return ResponseEntity.ok().body(topicoService.findAll());
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching topics", e);
        }
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
        try {
            Topico topico = topicoService.findById(id);

            if (topico == null) throw new ResourceNotFoundException(id);

            return ResponseEntity.ok().body(topico);
        }  catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching topic with ID: " + id, e);
        }
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
        try {
            Topico topico1 = topicoService.save(topico);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(topico1.getId()).toUri();
            return ResponseEntity.created(location).body(topico1);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while saving the topic", e);
        }
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
        try {
            if (topicoService.findById(id) == null) throw new ResourceNotFoundException(id);

            if (topicoDetails == null)
                throw new BadRequestException("topic Details cannot be null");

            return ResponseEntity.ok().body(topicoService.update(id, topicoDetails));

        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while updating the topic", e);
        }
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
        try {
            Topico topico = topicoService.findById(id);
            if (topico == null) throw new ResourceNotFoundException(id);

            if (!isUserAuthorizedToDelete(authToken,topico))
                throw new UnauthorizedException("You are not authorized to delete this topic");

            topicoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while deleting the topic", e);
        }
    }

    private boolean isUserAuthorizedToDelete(String authToken, Topico topico) {
        // Lógica para verificar se o usuário está autorizado a deletar o comentário
        return true;
    }

}
