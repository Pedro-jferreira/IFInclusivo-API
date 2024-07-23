package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.Exception.*;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.PK.ResolveuProblemaId;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.ResolveuProblema;
import com.example.IfGoiano.IfCoders.service.ResolveuProblemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resolveuproblemas")
public class ResolveuProblemaController {
    @Autowired
    private ResolveuProblemaService resolveuProblemaService;

    @Operation(summary = "Buscar todos os ResolveuProblemas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all solvedProblem",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Publicacao.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ResolveuProblema>> findAll() {
        try {
            return ResponseEntity.ok().body(resolveuProblemaService.findAll());
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching solvedProblems", e);
        }
    }

    @Operation(summary = "Buscar ResolveuProblema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the solvedProblem",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "solvedProblem not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ResolveuProblema> findById(@PathVariable ResolveuProblemaId id) {
        try {
            ResolveuProblema resolveuProblema = resolveuProblemaService.findById(id);

            if (resolveuProblema == null) throw new ResourceNotFoundException(id);

            return ResponseEntity.ok().body(resolveuProblema);
        }  catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching solvedProblem with ID: " + id, e);
        }
    }

    @Operation(summary = "Criar um novo ResolveuProblema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "solvedProblem created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ResolveuProblema> create(@RequestBody ResolveuProblema resolveuProblema) {
        try {
            ResolveuProblema resolveuProblema1 = resolveuProblemaService.save(resolveuProblema);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(resolveuProblema1.getId()).toUri();
            return ResponseEntity.created(location).body(resolveuProblema1);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while saving the solvedProblem", e);
        }
    }

    @Operation(summary = "Atualizar um ResolveuProblema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "solvedProblem updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "solvedProblem not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ResolveuProblema> update(@PathVariable ResolveuProblemaId id, @RequestBody ResolveuProblema resolveuProblemaDetails) {
        try {
            if (resolveuProblemaService.findById(id) == null) throw new ResourceNotFoundException(id);

            if (resolveuProblemaDetails == null)
                throw new BadRequestException("solvedProblem Details cannot be null");

            return ResponseEntity.ok().body(resolveuProblemaService.update(id, resolveuProblemaDetails));

        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while updating the solvedProblem", e);
        }
    }

    @Operation(summary = "Excluir um ResolveuProblema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "solvedProblem deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "solvedProblem not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ResolveuProblemaId id, @RequestHeader("Authorization") String authToken) {
        try {
            ResolveuProblema resolveuProblema = resolveuProblemaService.findById(id);
            if (resolveuProblema == null) throw new ResourceNotFoundException(id);

            if (!isUserAuthorizedToDelete(authToken,resolveuProblema))
                throw new UnauthorizedException("You are not authorized to delete this solvedProblem");

            resolveuProblemaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while deleting the solvedProblem", e);
        }
    }

    private boolean isUserAuthorizedToDelete(String authToken, ResolveuProblema resolveuProblema) {
        // Lógica para verificar se o usuário está autorizado a deletar o resolveuProblema
        return true;
    }

}
