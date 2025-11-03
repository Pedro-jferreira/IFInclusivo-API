package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasOutputDTOV2;
import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTOCreated;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.impl.LibrasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sinais")
@Tag(name = "Sinais de Libras")
public class LibrasController {

    @Autowired
    LibrasServiceImpl librasService;
    
    @Autowired
    UsuarioRepository usuarioRepository;


    @Operation(summary = "Buscar sinal por ID", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the sign",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InterpreteInputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Sign not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("find/{id}")
    public ResponseEntity<LibrasOutputDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(librasService.findById(id), HttpStatus.OK);
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar um novo sinal", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sign created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasOutputDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<LibrasOutputDTO> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do sinal a ser cadastrado", required = true,
            content = @Content(schema = @Schema(implementation = LibrasInputDTOCreated.class))) @org.springframework.web.bind.annotation.RequestBody LibrasInputDTOCreated sinais, @RequestParam Long idInterprete) {
        return new ResponseEntity<>(librasService.save(sinais,idInterprete), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Sugerir um novo sinal", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sign suggestion created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping(value = "/sugere", consumes = "multipart/form-data")
    public ResponseEntity<LibrasOutputDTO> sugereLibras(
            @ModelAttribute LibrasInputDTO sinais,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        var usuario = usuarioRepository.findByLogin(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        return new ResponseEntity<>(librasService.sugereLibras(sinais, usuario.getId()), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar sinal por ID", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibrasOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Sign not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<LibrasOutputDTO> getByIdLibras(@PathVariable Long id) {
        return new ResponseEntity<>(librasService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos os sinais com paginação", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signs retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping()
    public ResponseEntity<Page<LibrasOutputDTO>> getAllLibras(Pageable pageable) {
        Page<LibrasOutputDTO> libras = librasService.findAll(pageable);
        return ResponseEntity.ok(libras);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar um sinal por ID", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sign updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sign not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PutMapping("/update/{id}")
    public ResponseEntity<LibrasOutputDTO> updateLibras(
            @PathVariable Long id, 
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do sinal a ser atualizado", required = true,
                    content = @Content(schema = @Schema(implementation = LibrasInputDTO.class)))
            @RequestBody LibrasInputDTO sinais) {
        return new ResponseEntity<>(librasService.update(sinais, id), HttpStatus.NO_CONTENT);
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
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<LibrasOutputDTO> delete(@PathVariable Long id) {
        librasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Busca profunda por palavra em sinais", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/busca-profunda")
    public ResponseEntity<Page<LibrasOutputDTO>> buscaProfundaLibras(
            @RequestParam String palavra, Pageable pageable) {
        Page<LibrasOutputDTO> resultados = this.librasService.searchLibrasByDeeply(palavra, pageable);
        return ResponseEntity.ok(resultados);
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Buscar sinais por status", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signs found by status",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/busca-status")
    public ResponseEntity<Page<LibrasOutputDTO>> findByStatus(
            @RequestParam Status status, Pageable pageable) {
        Page<LibrasOutputDTO> resultados = this.librasService.findByStatus(status, pageable);
        return ResponseEntity.ok(resultados);
    }


    @Operation(summary = "Buscar sinais por palavra", tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signs found by word",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/busca-palavras")
    public ResponseEntity<Page<LibrasOutputDTO>> findByPalavras(@RequestParam String palavra, Pageable pageable) {
        Page<LibrasOutputDTO> palavras = this.librasService.findByPalavra(palavra, pageable);
        return ResponseEntity.ok(palavras);
    }

    @Operation(summary = "Buscar sinais por categoria", 
               description = "Categorias disponíveis: REDES, BANCO_DE_DADOS, PROGRAMACAO, WEB, ESTRUTURA_DE_DADOS, ARQUITETURA_DE_COMPUTADORES",
               tags = "Sinais de Libras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signs found by category",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/busca-categoria")
    public ResponseEntity<Page<LibrasOutputDTOV2>> findByCategoria(
            @RequestParam Categorias categoria, 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : 
            Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<LibrasOutputDTOV2> libras = this.librasService.findByCategoria(categoria, pageable);
        return ResponseEntity.ok(libras);
    }


}