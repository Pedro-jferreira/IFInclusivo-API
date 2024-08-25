package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.service.impl.InterpreteServiceImpl;
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
    InterpreteServiceImpl interpreteService;


    @Operation(summary = "Criar um novo interprete")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<InterpreteOutputDTO> createInterprete(@RequestBody InterpreteInputDTO interprete) {
        return new ResponseEntity<>(interpreteService.save(interprete), HttpStatus.CREATED);
    }



    @Operation(summary = "Buscar todos os Interpretes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Comments",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })

    @GetMapping
    public ResponseEntity<List<InterpreteOutputDTO>> getAllInterpretes() {
        return new ResponseEntity<>(interpreteService.findAll(), HttpStatus.OK);
    }


    @Operation(summary = "bscar Interpreter por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<InterpreteOutputDTO> getInterpreteById(@PathVariable Long id) {
        return new ResponseEntity<>(this.interpreteService.findById(id), HttpStatus.OK);
    }



    @Operation(summary = "Atualizar um interprete por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<InterpreteOutputDTO> updateInterprete(@RequestBody InterpreteInputDTO interprete, @PathVariable Long id) {
        return new ResponseEntity<>(this.interpreteService.update(interprete, id), HttpStatus.NO_CONTENT);
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
        interpreteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
