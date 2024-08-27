package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.ProfessorServiceImpl;
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
@RequestMapping("/professores")
@Tag(name = "Professor")
public class ProfessorController {

    @Autowired
    ProfessorServiceImpl professorService;

    @Operation(summary = "Buscar todos os professores", tags = "Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all professors",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/professores")
    public ResponseEntity<List<ProfessorOutputDTO>> findAll(){
        return ResponseEntity.ok(professorService.findAll());
    }

    @Operation(summary = "Buscar Professor por ID", tags = "Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the professor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Professor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorOutputDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(professorService.findById(id),HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar um novo Professor", tags = "Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<ProfessorOutputDTO> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do professor a ser cadastrado", required = true,
            content = @Content(schema = @Schema(implementation = ProfessorInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody ProfessorInputDTO professor) {
        return new ResponseEntity<>(professorService.save(professor), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um professor por ID", tags = "Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Professor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do professor a ser atualizado", required = true,
            content = @Content(schema = @Schema(implementation = ProfessorInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody ProfessorInputDTO professor){
        return new ResponseEntity<>(professorService.update(professor, id),HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Excluir um professor por ID", tags = "Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Professor deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Professor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/delete")
    public ResponseEntity<ProfessorOutputDTO> delete(Long id){
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}