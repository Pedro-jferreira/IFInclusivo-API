package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
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

import javax.websocket.server.PathParam;
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
                            schema = @Schema(implementation = LibrasOutputDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create/{idUser}")
    public ResponseEntity<LibrasOutputDTO> createLibras(@RequestBody LibrasInputDTO sinais, @PathVariable Long idUser){

        return new ResponseEntity<>(librasService.save(sinais, idUser), HttpStatus.CREATED);
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

    @DeleteMapping("/delete")
    public ResponseEntity<LibrasOutputDTO> deleteLibras(@PathVariable Long id){
        librasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}