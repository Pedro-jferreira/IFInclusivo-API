package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.AlunoNapneServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunosNapne")
@Tag(name = "Aluno NAPNE")
public class AlunoNapneController {
    @Autowired
    AlunoNapneServiceImpl alunoNapneService;

    @Operation(summary = "Buscar todos os alunos NAPNE", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Students NAPNE",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AlunoNapneOutputDTO>> findAll() {
        return new ResponseEntity<>(alunoNapneService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar aluno NAPNE por ID", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student NAPNE",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student NAPNE not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AlunoNapneOutputDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(alunoNapneService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar um novo aluno NAPNE", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student NAPNE created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AlunoNapneOutputDTO> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do aluno NAPNE a ser cadastrado", required = true,
            content = @Content(schema = @Schema(implementation = AlunoNapneInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody AlunoNapneInputDTO aluno) {
        return new ResponseEntity<>(alunoNapneService.save(aluno), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um aluno NAPNE por ID", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student NAPNE updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneOutputDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student NAPNE not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<AlunoNapneOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do aluno NAPNE a ser atualizado", required = true,
            content = @Content(schema = @Schema(implementation = AlunoNapneInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody AlunoNapneInputDTO aluno) {
       return new ResponseEntity<>(alunoNapneService.update(aluno,id), HttpStatus.OK);
    }

    @Operation(summary = "Excluir um aluno NAPNE por ID", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student NAPNE deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student NAPNE not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoNapneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}