package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
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
@RequestMapping("/publicacoes")
public class PublicacaoController {
    @Autowired
    private PublicacaoService service;

    @Operation(summary = "Buscar todas as Publicacões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PublicacaoOutputDTO>> findAll() {
           var publicacoes = service.findAll();
            return ResponseEntity.ok().body(publicacoes);
    }

    @Operation(summary = "Buscar publicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<PublicacaoOutputDTO> findById(@PathVariable Long id) {
        var publicacao = service.findById(id);
        return ResponseEntity.ok().body(publicacao);
    }

    @Operation(summary = "Criar uma nova Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<PublicacaoOutputDTO> create(@RequestBody(description = "informações para criar uma publicação",
                    required = true) @org.springframework.web.bind.annotation.RequestBody PublicacaoInputDTO publicacao) {
        var savedPublicacao = service.save(publicacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublicacao);
    }

    @Operation(summary = "Atualizar um publiicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<PublicacaoOutputDTO> update(@PathVariable Long id, @RequestBody(description = "informações para atualizar uma publicação",
                                                              required = true) @org.springframework.web.bind.annotation.RequestBody PublicacaoInputDTO publicacaoDetails) {
        var publicacao = service.update(id, publicacaoDetails);
            return ResponseEntity.ok().body(service.update(id, publicacaoDetails));
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
            service.delete(id);
            return ResponseEntity.noContent().build();
    }


}

