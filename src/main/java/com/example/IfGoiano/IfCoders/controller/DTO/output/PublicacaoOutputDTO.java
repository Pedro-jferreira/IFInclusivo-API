package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private SimpleUsuarioDTO usuario;
    private List<SimpleComentarioDTO> comentarios = new ArrayList<>();
    private List<SimpleUsuarioDTO> likeBy = new ArrayList<>();
    private List<SimpleTopicoDTO> topicos = new ArrayList<>();
}
