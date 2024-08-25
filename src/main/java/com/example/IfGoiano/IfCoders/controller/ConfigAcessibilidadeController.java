package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.service.impl.ConfigAcessibilidadeServiceImpl;
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
@RequestMapping("/configuracoesDeAcessibilidade")
public class ConfigAcessibilidadeController {
    @Autowired
    private ConfigAcessibilidadeServiceImpl configAcessibilidadeServiceImpl;

    @Operation(summary = "Buscar todas as configurações de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all accessibility configurations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcessibilidadeEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ConfigAcessibilidadeEntity>> findAll() {
        List<ConfigAcessibilidadeEntity> configAcessibilidadeEntity = configAcessibilidadeServiceImpl.findAll();
        return ResponseEntity.ok().body(configAcessibilidadeEntity);
    }

    @Operation(summary = "Buscar configuração de acessibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the accessibility configuration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcessibilidadeEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Accessibility configuration not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ConfigAcessibilidadeEntity> findById(@PathVariable Long id) {
        ConfigAcessibilidadeEntity configAcessibilidadeEntity = configAcessibilidadeServiceImpl.findById(id);
        return ResponseEntity.ok().body(configAcessibilidadeEntity);
    }

    @Operation(summary = "Cadastrar uma nova configuração de acessibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Accessibility configuration created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcessibilidadeEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ConfigAcessibilidadeEntity> save(@RequestBody ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        ConfigAcessibilidadeEntity savedConfigAcessibilidadeEntity = configAcessibilidadeServiceImpl.save(configAcessibilidadeEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedConfigAcessibilidadeEntity.getId()).toUri();
        return ResponseEntity.created(location).body(savedConfigAcessibilidadeEntity);
    }

    @Operation(summary = "Atualizar uma configuração de acessibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accessibility configuration updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConfigAcessibilidadeEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Accessibility configuration not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ConfigAcessibilidadeEntity> update(@PathVariable Long id, @RequestBody ConfigAcessibilidadeEntity configAcessibilidadeEntityDetails) {
        return ResponseEntity.ok().body(configAcessibilidadeServiceImpl.update(id, configAcessibilidadeEntityDetails));
    }

    @Operation(summary = "Excluir configuração de acessibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Accessibility configuration deleted",
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
