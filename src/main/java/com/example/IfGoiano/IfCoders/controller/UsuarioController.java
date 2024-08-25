package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.service.impl.UsuarioServiceImpl;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Operation(summary = "Buscar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> findAll() {
        List<UsuarioEntity> usuarios = usuarioServiceImpl.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable Long id) {
        UsuarioEntity usuario = usuarioServiceImpl.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @Operation(summary = "Criar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<UsuarioEntity> save(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity savedUsuario = usuarioServiceImpl.save(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(savedUsuario);
    }

    @Operation(summary = "Atualizar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @RequestBody UsuarioEntity usuarioDetails) {
        return ResponseEntity.ok().body(usuarioServiceImpl.update(id, usuarioDetails));
    }

    @Operation(summary = "Excluir usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        usuarioServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
