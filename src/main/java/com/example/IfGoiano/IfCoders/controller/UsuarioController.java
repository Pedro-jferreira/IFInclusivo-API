package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/user")
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Buscar usuario por ID", tags = "Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> findById(@PathVariable Long id) {
        var usuario = service.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @Operation(summary = "Buscar usuários por nome e/ou role", tags = "Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioOutputDTO.class))) })
    })
    @GetMapping("/search")
    public ResponseEntity<List<UsuarioOutputDTO>> searchUsers(
            @RequestParam(required = false) String name, // Parâmetro opcional para nome
            @RequestParam(required = false) Role role   // Parâmetro opcional para role
    ) {
        var usuarios = service.searchUsers(name, role);
        return ResponseEntity.ok(usuarios);
    }
}
