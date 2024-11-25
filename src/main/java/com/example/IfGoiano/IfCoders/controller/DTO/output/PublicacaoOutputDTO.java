package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacaoOutputDTO {

    private Long id;
    private String
            titulo;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime dataCriacao ;
    private SimpleComentarioDTO usuario;
    private SimpleTopicoDTO topico;
    private List<SimpleComentarioDTO> comentarios;
    private List<SimpleUsuarioDTO> likeBy;
}
