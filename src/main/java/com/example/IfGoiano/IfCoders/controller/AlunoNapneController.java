package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.model.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.InterpreteEntity;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.service.AlunoNapneService;
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
    AlunoNapneService alunoNapneService;

    @Operation(summary = "Criar um novo Aluno")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AlunoNapneEntity> createAluno(@RequestBody AlunoNapneEntity aluno) {
        return new ResponseEntity<>(alunoNapneService.createdAlunoNapne(aluno), HttpStatus.CREATED);
    }



    @Operation(summary = "Buscar todos os AlunosNapne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AlunoNapneEntity>> getAllAlunos() {
        return new ResponseEntity<>(alunoNapneService.findAllAlunoNapne(), HttpStatus.OK);
    }



    @Operation(summary = "Buscar Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoNapneEntity> getAlunoById(@PathVariable Long id) {
        AlunoNapneEntity aluno = alunoNapneService.findByIdAlunoNapne(id);
        return aluno != null ? new ResponseEntity<>(aluno, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Atualizar um Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoNapneEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<AlunoNapneEntity> updateAluno(@RequestBody AlunoNapneEntity aluno) {
        AlunoNapneEntity updatedAluno = alunoNapneService.updateAlunoNapne(aluno);
        return updatedAluno != null ? new ResponseEntity<>(updatedAluno, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Excluir um Aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoNapneService.deleteAlunoNapne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
