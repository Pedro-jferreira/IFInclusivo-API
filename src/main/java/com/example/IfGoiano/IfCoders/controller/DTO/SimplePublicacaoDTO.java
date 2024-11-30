package com.example.IfGoiano.IfCoders.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleUsuarioDTO usuario;
}
