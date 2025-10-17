package com.example.IfGoiano.IfCoders.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioSimplesDTO {
    private Long id;
    private String texto;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private PublicacaoUsuarioDTO usuario;
    private int totalLikes;
    private boolean curtidoPeloUsuario;
}
