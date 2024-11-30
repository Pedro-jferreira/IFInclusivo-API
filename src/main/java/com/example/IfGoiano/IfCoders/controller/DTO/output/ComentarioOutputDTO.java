package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComentarioOutputDTO {

    private Long id;
    private SimpleUsuarioDTO usuario;
    private SimplePublicacaoDTO publicacao;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleComentarioDTO comentarioPai;
    private List<SimpleComentarioDTO> comentariosFilhos;
    private List<SimpleUsuarioDTO> usefulBy;
}
