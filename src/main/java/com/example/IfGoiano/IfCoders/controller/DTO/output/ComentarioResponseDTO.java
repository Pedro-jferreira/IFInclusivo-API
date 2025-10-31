package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.PublicacaoUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioResponseDTO {
    private Long id;
    private String texto;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    private SimpleUsuarioDTO usuario;
    private SimpleUsuarioDTO usuarioMencionado;
    private Long publicacaoId;

    private int totalLikes;
    private boolean curtidoPeloUsuario;
    private int totalRespostas;

    private Long parentId;
}
