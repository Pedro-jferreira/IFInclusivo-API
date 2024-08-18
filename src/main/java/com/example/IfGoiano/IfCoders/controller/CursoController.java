package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.model.Curso;
import com.example.IfGoiano.IfCoders.service.CursoService;
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
    private CursoService cursoService;

    @Operation(summary = "Buscar todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all courses",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = cursoService.findAll();
        return ResponseEntity.ok().body(cursos);
    }

    @Operation(summary = "Buscar curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)) }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return ResponseEntity.ok().body(curso);
    }

    @Operation(summary = "Cadastrar um novo curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        Curso savedCurso = cursoService.save(curso);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCurso.getId()).toUri();
        return ResponseEntity.created(location).body(savedCurso);
    }

    @Operation(summary = "Atualizar um curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)) }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        return ResponseEntity.ok().body(cursoService.update(id, cursoDetails));
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
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
