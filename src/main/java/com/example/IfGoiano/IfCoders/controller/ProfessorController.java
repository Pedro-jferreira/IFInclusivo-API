package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.service.impl.ProfessorServiceImpl;
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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorServiceImpl professorService;


    @Operation(summary = "Criar um novo Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorInputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<ProfessorOutputDTO> createProfessor(@RequestBody ProfessorInputDTO professor) {
        return new ResponseEntity<>(professorService.save(professor), HttpStatus.CREATED);
    }


    @Operation(summary = "Buscar todas os professores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all Publication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/professores")
    public ResponseEntity<List<ProfessorOutputDTO>> getAllProfessor(){
        return ResponseEntity.ok(professorService.findAll());
    }


    @Operation(summary = "Buscar Professor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorOutputDTO> findByIdProfessor(@PathVariable Long id){
        return new ResponseEntity<>(professorService.findById(id),HttpStatus.OK);
    }


    @Operation(summary = "Atualizar informações do professor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorOutputDTO> updateProfessor(@RequestBody ProfessorInputDTO professor, @PathVariable Long id){
        return new ResponseEntity<>(professorService.update(professor, id),HttpStatus.NO_CONTENT);
    }



    @Operation(summary = "Excluir uma publicação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/delete")
    public ResponseEntity<ProfessorEntity> deleteProfessor(Long id){
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}