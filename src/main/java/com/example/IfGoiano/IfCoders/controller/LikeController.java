package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.Exception.*;
import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Like;
import com.example.IfGoiano.IfCoders.model.PK.LikeId;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Operation(summary = "Buscar todos as Likes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found all like",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Publicacao.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Like>> findAll() {
        try {
            List<Like> likes = likeService.findAll();
            return ResponseEntity.ok().body(likes);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching publications", e);
        }
    }

    @Operation(summary = "Buscar Like por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the like",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "like not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Like> findById(@PathVariable LikeId id) {
        try {
            Like like = likeService.findById(id);

            if (like == null) throw new ResourceNotFoundException(id);

            return ResponseEntity.ok().body(like);
        }  catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while fetching like with ID: " + id, e);
        }
    }

    @Operation(summary = "Criar uma nova Like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "like created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Like> create(@RequestBody Like like) {
        try {
            Like savedlike = likeService.save(like);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedlike.getId()).toUri();
            return ResponseEntity.created(location).body(savedlike);
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while saving the like", e);
        }
    }

    @Operation(summary = "Atualizar um like por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "like updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comentario.class)) }),
            @ApiResponse(responseCode = "404", description = "like not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Like> update(@PathVariable LikeId id, @RequestBody Like likeDetails) {
        try {
            if (likeService.findById(id) == null) throw new ResourceNotFoundException(id);

            if (likeDetails == null)
                throw new BadRequestException("LikeDetails cannot be null");

            return ResponseEntity.ok().body(likeService.update(id, likeDetails));

        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while updating the publication", e);
        }
    }

    @Operation(summary = "Excluir uma Like por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Like deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Like not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable LikeId id, @RequestHeader("Authorization") String authToken) {
        try {
            Like like = likeService.findById(id);
            if (like == null) throw new ResourceNotFoundException(id);

            if (!isUserAuthorizedToDelete(authToken,like))
                throw new UnauthorizedException("You are not authorized to delete this like");

            likeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DataBaseException e) {
            throw new InternalServerErrorException("Database error occurred while deleting the like", e);
        }
    }

    private boolean isUserAuthorizedToDelete(String authToken, Like like) {
        // Lógica para verificar se o usuário está autorizado a deletar o comentário
        return true;
    }


}
