package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.AlunoServiceImpl;
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
@RequestMapping("/alunos")
@Tag(name = "Aluno")
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @Operation(summary = "Buscar todos os alunos", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all students",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AlunoOutputDTO>> findAll() {
        var alunos = alunoServiceImpl.findAll();
        return ResponseEntity.ok().body(alunos);
    }

    @Operation(summary = "Buscar aluno por ID", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoOutputDTO> findById(@PathVariable Long id) {
        var aluno = alunoServiceImpl.findById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @Operation(summary = "Cadastrar um novo aluno", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<AlunoOutputDTO> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do aluno a ser cadastrado",
                    required = true,
            content = @Content(schema = @Schema(implementation = AlunoInputDTO.class)))
            @RequestParam Long idCurso,
            @RequestParam Long idConfigAc,
            @org.springframework.web.bind.annotation.RequestBody AlunoInputDTO aluno) {
        var savedAluno = alunoServiceImpl.save(aluno,idCurso,idConfigAc);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAluno.getId()).toUri();
        return ResponseEntity.created(location).body(savedAluno);
    }

    @Operation(summary = "Atualizar um aluno por ID", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<AlunoOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do aluno a ser atualizado", required = true,
            content = @Content(schema = @Schema(implementation = AlunoInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody AlunoInputDTO alunoDetails) {
        return ResponseEntity.ok().body(alunoServiceImpl.update(id, alunoDetails));
    }

    @Operation(summary = "Excluir aluno por ID", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        alunoServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }


}
