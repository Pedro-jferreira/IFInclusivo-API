package com.example.IfGoiano.IfCoders.controller;



import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.InterpreteUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.service.InterpreteService;
import com.example.IfGoiano.IfCoders.service.impl.AnalisarLibras;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/interpretes")
@Tag(name = "Intérprete")
public class InterpreteController {

    @Autowired
    InterpreteService interpreteService;

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
    @PutMapping()
    public ResponseEntity<?> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do intérprete a ser atualizado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = InterpreteInputDTO.class))
            )
            @org.springframework.web.bind.annotation.RequestBody InterpreteUpdateDTO interprete,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
        return new ResponseEntity<>(this.interpreteService.update(interprete, userDetails.getUsername()), HttpStatus.NO_CONTENT);
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



    @Operation(summary = "Analisar palavra em Libras", tags = "Intérprete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Palavra analisada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Intérprete não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping("/analisar/{idInterprete}")
    public ResponseEntity<LibrasOutputDTO> analisarLibras(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da palavra a ser analisada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RequestAnalisePalavra.class))
            )
            @RequestBody RequestAnalisePalavra requestAnalisePalavra, 
            @PathVariable Long idInterprete){

        return new ResponseEntity<>(this.analisarLibras.analisarPalavra(requestAnalisePalavra, idInterprete), HttpStatus.CREATED);
    }


    @GetMapping("/historico-sugeridas")
    public ResponseEntity<Page<LibrasOutputDTO>> historicoLibras(Pageable pageable){
        return new ResponseEntity<>(this.interpreteService.historicoLibrasSugeridas(pageable), HttpStatus.OK);

    }
}
