package com.example.IfGoiano.IfCoders.controller;


import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.security.access.AccessDeniedException;
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
            description = "Cria uma nova publicação (tópico principal)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublicacaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: título faltando, validação do DTO falhou)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado (token inválido ou ausente)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (ex: `parentId` ou usuário não existem)", content = @Content)
    })
    public ResponseEntity<PublicacaoResponseDTO> create(
            @RequestBody @Valid PublicacaoRequestDTO publicacaoDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        var savedPublicacao = publicacaoService.save(publicacaoDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublicacao);
    }


    @GetMapping
    @Operation(summary = "Lista todas as publicações principais de forma paginada",
            description = "Permite filtrar por categorias e ordenar por relevância ou data.")
    public Page<PublicacaoResponseDTO> getFeed(
            @RequestParam(required = false) Set<Categorias> categorias,
            @RequestParam(defaultValue = "MAIS_RECENTE") Ordenacao ordenarPor,
            Pageable pageable,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = (userDetails != null) ? userDetails.getUsername() : null;

        return publicacaoService.findAll(categorias, ordenarPor, pageable, username);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar uma publicação por ID", tags = "Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication updated",
                    content = @Content(schema = @Schema(implementation = PublicacaoResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Publication not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualizar uma publicação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PublicacaoRequestDTO.class))
            ) @RequestBody PublicacaoRequestDTO publicacaoDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String username = userDetails.getUsername();

        try {
            PublicacaoResponseDTO updated = publicacaoService.update(id, publicacaoDetails, username);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }


    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Excluir uma publicação por ID", tags = "Publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publication deleted", content = @Content),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Publication not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String username = userDetails.getUsername();

        try {
            publicacaoService.delete(id, username);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca uma publicação por ID",
            description = "Retorna a publicação pelo ID junto com sua árvore de pais (sem incluir os filhos)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicacaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada", content = @Content)
    })
    public PublicacaoResponseDTO getById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = (userDetails != null) ? userDetails.getUsername() : null;
        return publicacaoService.findById(id, username);
    }

}

