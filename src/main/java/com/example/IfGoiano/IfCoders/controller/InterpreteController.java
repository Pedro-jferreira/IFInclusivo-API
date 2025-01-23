package com.example.IfGoiano.IfCoders.controller;



import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.AnalisarLibras;
import com.example.IfGoiano.IfCoders.service.impl.InterpreteServiceImpl;
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
@RequestMapping("/interpretes")
@Tag(name = "Intérprete")
public class InterpreteController {

    @Autowired
    InterpreteServiceImpl interpreteService;

    @Autowired
    AnalisarLibras analisarLibras;

    @Operation(summary = "Buscar todos os intérpretes", tags = "Intérprete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Interpreters",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<InterpreteOutputDTO>> findAll() {
        return new ResponseEntity<>(interpreteService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar intérprete por ID", tags = "Intérprete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Interpreter",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Interpreter not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<InterpreteOutputDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.interpreteService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar um novo intérprete", tags = "Intérprete")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Interpreter created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<InterpreteOutputDTO> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do intérprete a ser cadastrado",
                    required = true,
            content = @Content(schema = @Schema(implementation = InterpreteInputDTO.class)))
            @RequestParam Long idConfigAc,
            @org.springframework.web.bind.annotation.RequestBody InterpreteInputDTO interprete) {
        return new ResponseEntity<>(interpreteService.save(interprete,idConfigAc), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um intérprete por ID", tags = "Intérprete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interpreter updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Interpreter not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<InterpreteOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do intérprete a ser atualizado", required = true,
            content = @Content(schema = @Schema(implementation = InterpreteInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody InterpreteInputDTO interprete) {
        return new ResponseEntity<>(this.interpreteService.update(interprete, id), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletar um intérprete por ID", tags = "Intérprete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Interpreter deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Interpreter not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        interpreteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping("/analisar/{idInterprete}")
    public ResponseEntity<LibrasOutputDTO> analisarLibras(@RequestBody RequestAnalisePalavra requestAnalisePalavra, @PathVariable Long idInterprete){

        return new ResponseEntity<>(this.analisarLibras.analisarPalavra(requestAnalisePalavra, idInterprete), HttpStatus.CREATED);
    }
}
