package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.ConfigAcessibilidadeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/configuracoesDeAcessibilidade")
@Tag(name = "Configurações de acessibilidade")
public class ConfigAcessibilidadeController {
    @Autowired
    private ConfigAcessibilidadeServiceImpl configAcessibilidadeServiceImpl;

    @Operation(summary = "Buscar todas as configurações de acessibilidade", tags = "Configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all accessibility configurations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcblOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ConfigAcblOutputDTO>> findAll() {
        var configAcessibilidadeEntity = configAcessibilidadeServiceImpl.findAll();
        return ResponseEntity.ok().body(configAcessibilidadeEntity);
    }

    @Operation(summary = "Buscar configuração de acessibilidade por ID", tags = "Configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the accessibility configuration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcblOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Accessibility configuration not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ConfigAcblOutputDTO> findById(@PathVariable Long id) {
        var configAcessibilidadeEntity = configAcessibilidadeServiceImpl.findById(id);
        return ResponseEntity.ok().body(configAcessibilidadeEntity);
    }

    @Operation(summary = "Cadastrar uma nova configuração de acessibilidade", tags = "Configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Accessibility configuration created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcblOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ConfigAcblOutputDTO> save(
                   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da configuração de acessibilidade a ser cadastrada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ConfigAcblInputDTO.class)))
                   @org.springframework.web.bind.annotation.RequestBody ConfigAcblInputDTO configAcessibilidadeEntity,
                   @RequestParam Long userId) {
        var savedConfigAcessibilidadeEntity = configAcessibilidadeServiceImpl.save(configAcessibilidadeEntity,userId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedConfigAcessibilidadeEntity.getId()).toUri();
        return ResponseEntity.created(location).body(savedConfigAcessibilidadeEntity);
    }

    @Operation(summary = "Atualizar uma configuração de acessibilidade por ID", tags = "Configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accessibility configuration updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcblOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Accessibility configuration not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ConfigAcblOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da configuração de acessibilidade a ser atualizada", required = true,
            content = @Content(schema = @Schema(implementation = ConfigAcblInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody ConfigAcblInputDTO configAcessibilidadeEntityDetails) {
        return ResponseEntity.ok().body(configAcessibilidadeServiceImpl.update(id, configAcessibilidadeEntityDetails));
    }

    @Operation(summary = "Excluir configuração de acessibilidade por ID", tags = "Configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Accessibility configuration deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Accessibility configuration not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        configAcessibilidadeServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
