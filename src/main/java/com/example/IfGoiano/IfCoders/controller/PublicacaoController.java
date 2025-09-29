package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoCompletaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDetalhadaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/publicacoes")
@RequiredArgsConstructor
@Tag(name = "Publicações", description = "Endpoints para gerenciar publicações e respostas")
public class PublicacaoController {
    private final PublicacaoService publicacaoService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Cria uma nova publicação ou resposta",
            description = "Cria uma nova publicação (tópico principal) se `parentId` for nulo, ou cria uma resposta a uma publicação existente se `parentId` for informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublicacaoOutputDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: título faltando, validação do DTO falhou)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado (token inválido ou ausente)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (ex: `parentId` ou usuário não existem)", content = @Content)
    })
    public ResponseEntity<PublicacaoDetalhadaDTO> create(
            @RequestBody @Valid PublicacaoRequestDTO publicacaoDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        var savedPublicacao = publicacaoService.save(publicacaoDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublicacao);
    }


    @GetMapping
    @Operation(summary = "Lista todas as publicações principais de forma paginada",
            description = "Permite filtrar por categorias e ordenar por relevância ou data.")
    public Page<PublicacaoDetalhadaDTO> getFeed(
            @RequestParam(required = false) Set<Categorias> categorias,
            @RequestParam(defaultValue = "MAIS_RECENTE") Ordenacao ordenarPor,
            Pageable pageable,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = (userDetails != null) ? userDetails.getUsername() : null;

        return publicacaoService.findAll(categorias, ordenarPor, pageable, username);
    }


    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar um publicação por ID", tags = "Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<PublicacaoOutputDTO> update(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para atualizar uma publicação", required = true,
            content = @Content(schema = @Schema(implementation = PublicacaoRequestDTO.class))) @RequestBody PublicacaoRequestDTO publicacaoDetails) {

        return ResponseEntity.ok().body(publicacaoService.update(id, publicacaoDetails));
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Excluir uma publicação por ID", tags = "Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String authToken) {
        publicacaoService.delete(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca uma publicação por ID",
            description = "Retorna a publicação pelo ID junto com sua árvore de pais (sem incluir os filhos)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoCompletaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada", content = @Content)
    })
    public PublicacaoCompletaDTO getById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = (userDetails != null) ? userDetails.getUsername() : null;
        return publicacaoService.findById(id, username);
    }

    @GetMapping("/{id}/respostas")
    @Operation(
            summary = "Lista as respostas de uma publicação",
            description = "Retorna as respostas (filhos) de uma publicação, permitindo ordenar por relevância ou mais recentes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respostas listadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PublicacaoDetalhadaDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada", content = @Content)
    })
    public Page<PublicacaoDetalhadaDTO> getRespostas(
            @PathVariable Long id,
            @RequestParam(defaultValue = "MAIS_RECENTE") Ordenacao ordenarPor,
            Pageable pageable,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = (userDetails != null) ? userDetails.getUsername() : null;
        return publicacaoService.findFilhosById(id, ordenarPor, pageable, username);
    }


}

