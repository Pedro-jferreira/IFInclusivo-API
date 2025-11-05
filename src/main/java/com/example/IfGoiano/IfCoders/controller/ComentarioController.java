package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioResponseDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.service.impl.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
@Tag(name = "Comentários", description = "Endpoints para gerenciar comentários e respostas")
public class ComentarioController {
    private final ComentarioService comentarioService;


    @PostMapping("/publicacao/{publicacaoId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Adicionar comentário em uma publicação")
    public ResponseEntity<ComentarioResponseDTO> adicionarComentario(
            @PathVariable Long publicacaoId,
            @Valid @RequestBody ComentarioRequestDTO comentarioDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        var username = userDetails.getUsername();
        var response = comentarioService.adicionarComentario(publicacaoId, comentarioDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/{comentarioId}/like")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Curtir ou remover curtida de um comentário")
    public ResponseEntity<?> curtirComentario(
            @PathVariable Long comentarioId,
            @AuthenticationPrincipal UserDetails userDetails) {

        var username = userDetails.getUsername();
        comentarioService.toggleCurtir(comentarioId, username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{comentarioId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Editar um comentário existente")
    public ResponseEntity<?> editarComentario(
            @PathVariable Long comentarioId,
            @Valid @RequestBody ComentarioRequestDTO comentarioDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            var username = userDetails.getUsername();
            var atualizado = comentarioService.editarComentario(comentarioId, comentarioDTO, username);
            return ResponseEntity.ok(atualizado);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{comentarioId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Excluir um comentário existente")
    public ResponseEntity<?> excluirComentario(
            @PathVariable Long comentarioId,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            if (userDetails == null || userDetails.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário não autenticado. É necessário estar logado para excluir um comentário.");
            }

            var username = userDetails.getUsername();
            comentarioService.excluirComentario(comentarioId, username);
            return ResponseEntity.noContent().build(); // 204

        } catch (AccessDeniedException | SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao excluir comentário: " + e.getMessage());
        }
    }


    @GetMapping("/publicacao/{publicacaoId}")
    @Operation(summary = "Listar comentários de uma publicação")
    public Page<ComentarioResponseDTO> listarComentariosPorPublicacao(
            @PathVariable Long publicacaoId,
            @RequestParam(defaultValue = "MAIS_RECENTE") Ordenacao ordenacao,
            Pageable pageable,
            @AuthenticationPrincipal UserDetails userDetails) {

        var username = (userDetails != null) ? userDetails.getUsername() : null;
        return comentarioService.listarComentariosPublicacao(publicacaoId,ordenacao, pageable, username);
    }


    @GetMapping("/respostas/{comentarioId}")
    @Operation(summary = "Listar respostas de um comentário")
    public Page<ComentarioResponseDTO> listarRespostasDoComentario(
            @PathVariable Long comentarioId,
            Pageable pageable,
            @AuthenticationPrincipal UserDetails userDetails) {

        var username = (userDetails != null) ? userDetails.getUsername() : null;
        return comentarioService.listarRespostasComentario(comentarioId, pageable, username);
    }

}
