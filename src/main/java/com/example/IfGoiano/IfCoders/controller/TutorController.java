package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.TutorServiceImpl;
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
@RequestMapping("/tutores")
@Tag(name = "Tutor")
public class TutorController {

    @Autowired
    TutorServiceImpl service;

    @Operation(summary = "Buscar todas os tutores", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all tutors",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TutorOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TutorOutputDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar tutor por ID", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the tutor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TutorOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TutorOutputDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar um novo tutor", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutor created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TutorInputDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<TutorOutputDTO> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para cadastrar um tutor", required = true,
            content = @Content(schema = @Schema(implementation = TutorInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody TutorInputDTO tutor) {
        return new ResponseEntity<>(service.save(tutor), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um tutor por ID", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TutorOutputDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TutorOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para atualizar um tutor", required = true,
            content = @Content(schema = @Schema(implementation = TutorInputDTO.class))) @org.springframework.web.bind.annotation.RequestBody TutorInputDTO tutor) {
        return new ResponseEntity<>(this.service.update(tutor, id), HttpStatus.OK);
    }

    @Operation(summary = "Excluir um tutor por ID", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tutor deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}


