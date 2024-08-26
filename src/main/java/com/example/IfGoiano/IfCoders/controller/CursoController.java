package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.CursoServiceImpl;
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
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoServiceImpl cursoServiceImpl;

    @Operation(summary = "Buscar todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all courses",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CursoOutputDTO>> findAll() {
        var cursoEntities = cursoServiceImpl.findAll();
        return ResponseEntity.ok().body(cursoEntities);
    }

    @Operation(summary = "Buscar curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<CursoOutputDTO> findById(@PathVariable Long id) {
        var cursoEntity = cursoServiceImpl.findById(id);
        return ResponseEntity.ok().body(cursoEntity);
    }

    @Operation(summary = "Cadastrar um novo cursoEntity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<CursoOutputDTO> save(@RequestBody CursoInputDTO cursoEntity) {
        var savedCursoEntity = cursoServiceImpl.save(cursoEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCursoEntity.getId()).toUri();
        return ResponseEntity.created(location).body(savedCursoEntity);
    }

    @Operation(summary = "Atualizar um curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<CursoOutputDTO> update(@PathVariable Long id, @RequestBody CursoInputDTO cursoEntityDetails) {
        return ResponseEntity.ok().body(cursoServiceImpl.update(id, cursoEntityDetails));
    }

    @Operation(summary = "Excluir curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        cursoServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
