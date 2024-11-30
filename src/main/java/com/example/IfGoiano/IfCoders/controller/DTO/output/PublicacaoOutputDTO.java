package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacaoOutputDTO {
    private Long id;
    private String titulo;
    private String text;
    private String urlVideo;
    private String urlFoto;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao ;
    private SimpleComentarioDTO usuario;
    private SimpleTopicoDTO topico;
    private List<SimpleComentarioDTO> comentarios;
    private List<SimpleUsuarioDTO> likeBy;
}
