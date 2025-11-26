package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoNapneUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.service.impl.AlunoNapneServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<AlunoNapneOutputDTO> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do aluno NAPNE a ser cadastrado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AlunoNapneInputDTO.class)))
            @RequestParam Long idConfigAc,
            @org.springframework.web.bind.annotation.RequestBody AlunoNapneInputDTO aluno) {
        return new ResponseEntity<>(alunoNapneService.save(aluno, idConfigAc), HttpStatus.CREATED);
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
    @PutMapping()
    public ResponseEntity<?> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do aluno NAPNE a ser atualizado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AlunoNapneUpdateDTO.class))
            )
            @org.springframework.web.bind.annotation.RequestBody AlunoNapneUpdateDTO aluno,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
        return new ResponseEntity<>(alunoNapneService.update(aluno, userDetails.getUsername()), HttpStatus.OK);
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
    
    @Operation(summary = "Buscar alunos por termo", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/buscar-alunos")
    public ResponseEntity<List<SimpleAlunoDTO>> buscarAlunos(@RequestParam String termo) {
        return ResponseEntity.ok(alunoNapneService.buscarAlunosPorTermo(termo));
    }
    
    @Operation(summary = "Editar aluno NAPNE por ID", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student NAPNE updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Student NAPNE not found",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<AlunoNapneOutputDTO> editarAlunoNapne(
            @PathVariable Long id, 
            @RequestBody AlunoNapneUpdateDTO dto) {
        return ResponseEntity.ok(alunoNapneService.editarAlunoNapne(id, dto));
    }
    
    @Operation(summary = "Converter aluno para NAPNE", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student converted to NAPNE",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)})
    @PostMapping("/converter-aluno/{alunoId}")
    public ResponseEntity<AlunoNapneOutputDTO> converterParaNapne(@PathVariable Long alunoId) {
        return ResponseEntity.ok(alunoNapneService.converterAlunoParaNapne(alunoId));
    }
    
    @Operation(summary = "Converter NAPNE para aluno", tags = "Aluno NAPNE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "NAPNE converted to student",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "NAPNE not found",
                    content = @Content)})
    @PostMapping("/converter-para-aluno/{alunoNapneId}")
    public ResponseEntity<AlunoOutputDTO> converterParaAluno(@PathVariable Long alunoNapneId) {
        return ResponseEntity.ok(alunoNapneService.converterNapneParaAluno(alunoNapneId));
    }
}