package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.Exception.*;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
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
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoService publicacaoService;

    @Operation(summary = "Buscar todas as Publicacões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Publicacao.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Publicacao>> findAll() {
        try {
            List<Publicacao> publicacoes = publicacaoService.findAll();
            return ResponseEntity.ok().body(publicacoes);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching publications", e);
        }
    }

    @Operation(summary = "Buscar publicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> findById(@PathVariable Long id) {
        try {
            Publicacao publicacao = publicacaoService.findById(id);

            if (publicacao == null) throw new ResourceNotFoundException(id);

            return ResponseEntity.ok().body(publicacao);
        }  catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching publication with ID: " + id, e);
        }
    }

    @Operation(summary = "Criar uma nova Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Publicacao> create(@RequestBody Publicacao publicacao) {
        try {
            Publicacao savedPublicacao = publicacaoService.save(publicacao);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedPublicacao.getId()).toUri();
            return ResponseEntity.created(location).body(savedPublicacao);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while saving the publication", e);
        }
    }

    @Operation(summary = "Atualizar um publiicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> update(@PathVariable Long id, @RequestBody Publicacao publicacaoDetails) {
        try {
            if (publicacaoService.findById(id) == null) throw new ResourceNotFoundException(id);

            if (publicacaoDetails == null)
                throw new BadRequestException("publication details cannot be null");

            return ResponseEntity.ok().body(publicacaoService.update(id, publicacaoDetails));

        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while updating the publication", e);
        }
    }

    @Operation(summary = "Excluir uma publicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        try {
            Publicacao publicacao = publicacaoService.findById(id);
            if (publicacao == null) throw new ResourceNotFoundException(id);

            if (!isUserAuthorizedToDelete(authToken,publicacao))
                throw new UnauthorizedException("You are not authorized to delete this publication");

            publicacaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while deleting the publication", e);
        }
    }

    private boolean isUserAuthorizedToDelete(String authToken, Publicacao publicacao) {
        // Lógica para verificar se o usuário está autorizado a deletar o comentário
        return true;
    }
}

