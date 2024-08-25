package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.service.impl.PublicacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {
    @Autowired
    private PublicacaoServiceImpl publicacaoServiceImpl;

//    @Operation(summary = "Buscar todas as Publicacões")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",description = "Found all Publication",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = PublicacaoDTO.class))}),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = @Content)
//    })
//    @GetMapping
//    public ResponseEntity<List<PublicacaoDTO>> findAll() {
//            List<PublicacaoDTO> publicacoes = publicacaoServiceImpl.findAll();
//            return ResponseEntity.ok().body(publicacoes);
//    }
//
//    @Operation(summary = "Buscar publicação por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the publication",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = PublicacaoDTO.class)) }),
//            @ApiResponse(responseCode = "404", description = "publication not found",
//                    content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = @Content) })
//    @GetMapping("/{id}")
//    public ResponseEntity<PublicacaoDTO> findById(@PathVariable Long id) {
//        PublicacaoDTO publicacao = publicacaoServiceImpl.findById(id);
//            return ResponseEntity.ok().body(publicacao);
//    }
//
//    @Operation(summary = "Criar uma nova Publicação")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "publication created",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ComentarioEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = @Content) })
//    @PostMapping
//    public ResponseEntity<PublicacaoDTO> create(@RequestBody PublicacaoDTO publicacao) {
//
//        PublicacaoDTO savedPublicacao = publicacaoServiceImpl.save(publicacao);
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                    .buildAndExpand(savedPublicacao.getId()).toUri();
//            return ResponseEntity.created(location).body(savedPublicacao);
//    }
//
//    @Operation(summary = "Atualizar um publiicação por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "publication updated",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = PublicacaoDTO.class)) }),
//            @ApiResponse(responseCode = "404", description = "publication not found",
//                    content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = @Content) })
//    @PutMapping("/{id}")
//    public ResponseEntity<PublicacaoDTO> update(@PathVariable Long id, @RequestBody PublicacaoDTO publicacaoDetails) {
//
//            return ResponseEntity.ok().body(publicacaoServiceImpl.update(id, publicacaoDetails));
//    }
//
//    @Operation(summary = "Excluir uma publicação por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "publication deleted",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "publication not found",
//                    content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = @Content) })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
//            publicacaoServiceImpl.delete(id);
//            return ResponseEntity.noContent().build();
//    }


}

