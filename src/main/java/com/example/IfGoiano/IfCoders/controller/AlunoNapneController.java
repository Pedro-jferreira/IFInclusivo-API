package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.service.impl.AlunoNapneServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoNapneController {

    @Autowired
    AlunoNapneServiceImpl alunoNapneService;

    @Operation(summary = "Criar um novo Aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AlunoNapneOutputDTO> createAluno(@RequestBody AlunoNapneInputDTO aluno) {
        return new ResponseEntity<>(alunoNapneService.save(aluno), HttpStatus.CREATED);
    }


    @Operation(summary = "Buscar todos os AlunosNapne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AlunoNapneOutputDTO>> getAllAlunos() {
        return new ResponseEntity<>(alunoNapneService.findAll(), HttpStatus.OK);
    }


    @Operation(summary = "Buscar Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class))}),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AlunoNapneOutputDTO> getAlunoById(@PathVariable Long id) {
      return new ResponseEntity<>(alunoNapneService.findById(id), HttpStatus.OK);
    }


    @Operation(summary = "Atualizar um Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class))}),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<AlunoNapneOutputDTO> updateAluno(@RequestBody AlunoNapneInputDTO aluno, @PathVariable Long id) {
       return new ResponseEntity<>(alunoNapneService.update(aluno,id), HttpStatus.OK);
    }


    @Operation(summary = "Excluir um Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoNapneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}