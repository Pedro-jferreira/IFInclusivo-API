package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.service.impl.LibrasServiceImpl;
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
@RequestMapping("/sinais")
@Tag(name = "Sinais de Libras")
public class LibrasController {

    @Autowired
    LibrasServiceImpl librasService;

    @Operation(summary = "Buscar todos os sinais", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all signs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/findall")
    public ResponseEntity<List<LibrasOutputDTO>> findAll(){
        return new ResponseEntity<>(librasService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar sinal por ID", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the sign",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Sign not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("find/{id}")
    public ResponseEntity<LibrasOutputDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(librasService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar um novo sinal", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sign created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<LibrasOutputDTO> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do sinal a ser cadastrado", required = true,
            content = @Content(schema = @Schema(implementation = LibrasInputDTO.class)))  @org.springframework.web.bind.annotation.RequestBody LibrasInputDTO sinais){
        return new ResponseEntity<>(librasService.save(sinais), HttpStatus.CREATED);
    }


    @PostMapping("/sugere/{id}")
    public ResponseEntity<LibrasOutputDTO> sugereLibras(@RequestBody LibrasInputDTO sinais,@PathVariable Long id){

        return new ResponseEntity<>(librasService.sugereLibras(sinais,id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrasOutputDTO> getByIdLibras(@PathVariable Long id){

        return new ResponseEntity<>(librasService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<LibrasOutputDTO>> getAllLibras(){

        return new ResponseEntity<>(librasService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<LibrasOutputDTO> updateLibras(@PathVariable Long id,@RequestBody LibrasInputDTO sinais){
        return new ResponseEntity<>(librasService.update(sinais,id), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletar um sinal por ID", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sign deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sign not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LibrasOutputDTO> delete(@PathVariable Long id){
        librasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}