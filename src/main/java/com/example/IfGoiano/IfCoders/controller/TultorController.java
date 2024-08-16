package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.TultorEntity;
import com.example.IfGoiano.IfCoders.service.TultorService;
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
@RequestMapping("/tutor")
public class TultorController {

    @Autowired
    TultorService service;


    @Operation(summary = "Criar um novo Tultor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TultorEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<TultorEntity> createTutor(@RequestBody TultorEntity tultor) {
        return new ResponseEntity<>(service.createdTultor(tultor), HttpStatus.CREATED);
    }


    @Operation(summary = "Buscar todas as Publicacões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TultorEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TultorEntity>> getAllTutors() {
        return new ResponseEntity<>(service.findAllTultor(), HttpStatus.OK);
    }



    @Operation(summary = "Buscar Tultor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TultorEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<TultorEntity> getTutorById(@PathVariable Long id) {
        TultorEntity tutor = service.findByIdTultor(id);
        return tutor != null ? new ResponseEntity<>(tutor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Atualizar um publiicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TultorEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<TultorEntity> updateTutor(@RequestBody TultorEntity tultor) {
        TultorEntity updatedTutor = service.updateTultor(tultor);
        return updatedTutor != null ? new ResponseEntity<>(updatedTutor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Excluir uma publicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        service.deleteTultor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}


