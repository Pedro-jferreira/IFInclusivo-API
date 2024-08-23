package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.model.Libras;

import com.example.IfGoiano.IfCoders.service.LibrasService;
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
@RequestMapping("/sinais")
public class LibrasController {

    @Autowired
    LibrasService librasService;


    @Operation(summary = "Criar uma nova Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libras.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<Libras> createLibras(@RequestBody Libras sinais){

        return new ResponseEntity<>(librasService.createLibras(sinais), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libras> getByIdLibras(@PathVariable Long id){

        return new ResponseEntity<>(librasService.findLibrasById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Libras>> getAllLibras(){

        return new ResponseEntity<>(librasService.findAllLibras(), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Libras> updateLibras(@RequestBody Libras sinais){
        return new ResponseEntity<>(librasService.updateLibras(sinais), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Libras> deleteLibras(@PathVariable Long id){
        librasService.deleteLibras(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}