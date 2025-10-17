package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.PublicacaoUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioResponseDTO {
    private Long id;
    private String texto;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    private PublicacaoUsuarioDTO usuario;
    private PublicacaoUsuarioDTO usuarioMencionado;
    private Long publicacaoId;

    private int totalLikes;
    private boolean curtidoPeloUsuario;
    private int totalRespostas;

    private Long parentId;
}
