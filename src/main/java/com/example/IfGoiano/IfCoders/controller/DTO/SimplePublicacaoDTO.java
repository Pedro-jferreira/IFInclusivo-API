package com.example.IfGoiano.IfCoders.controller.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimplePublicacaoDTO {
    private Long id;
    private String
            titulo;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime dataCriacao;
    private SimpleUsuarioDTO usuario;
}
