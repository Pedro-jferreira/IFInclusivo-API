package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.service.impl.LibrasServiceImpl;
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
    LibrasServiceImpl librasService;


    @Operation(summary = "Criar uma nova Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<LibrasEntity> createLibras(@RequestBody LibrasEntity sinais){

        return new ResponseEntity<>(librasService.save(sinais), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrasEntity> getByIdLibras(@PathVariable Long id){

        return new ResponseEntity<>(librasService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<LibrasEntity>> getAllLibras(){

        return new ResponseEntity<>(librasService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<LibrasEntity> updateLibras(@RequestBody LibrasEntity sinais){
        return new ResponseEntity<>(librasService.update(sinais), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<LibrasEntity> deleteLibras(@PathVariable Long id){
        librasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}