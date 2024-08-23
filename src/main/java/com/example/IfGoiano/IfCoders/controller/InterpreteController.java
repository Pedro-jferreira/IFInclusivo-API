package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.model.Interprete;

import com.example.IfGoiano.IfCoders.service.InterpreteService;
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
@RequestMapping("/interprete")
public class InterpreteController {

    @Autowired
    InterpreteService interpreteService;


    @Operation(summary = "Criar um novo interprete")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interprete.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Interprete> createInterprete(@RequestBody Interprete interprete) {
        return new ResponseEntity<>(interpreteService.createdInterprete(interprete), HttpStatus.CREATED);
    }



    @Operation(summary = "Buscar todos os Interpretes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Comments",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interprete.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })

    @GetMapping
    public ResponseEntity<List<Interprete>> getAllInterpretes() {
        return new ResponseEntity<>(interpreteService.findAllInterpretes(), HttpStatus.OK);
    }


    @Operation(summary = "bscar Interpreter por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interprete.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Interprete> getInterpreteById(@PathVariable Long id) {
        Interprete interprete = interpreteService.findByIdInterprete(id);
        return interprete != null ? new ResponseEntity<>(interprete, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Atualizar um interprete por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interprete.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Interprete> updateInterprete(@RequestBody Interprete interprete) {
        Interprete updatedInterprete = interpreteService.updateInterprete(interprete);
        return updatedInterprete != null ? new ResponseEntity<>(updatedInterprete, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Deletar um Interprete por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterprete(@PathVariable Long id) {
        interpreteService.deleteInterprete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
