package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComentarioOutputDTO {

    private Long id;
    private SimpleUsuarioDTO usuario;
    @JsonIgnore
    private SimplePublicacaoDTO publicacao;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleComentarioDTO comentarioPai;
    @JsonIgnore
    private List<SimpleComentarioDTO> comentariosFilhos = new ArrayList<>();
    @JsonIgnore
    private List<SimpleUsuarioDTO> usefulBy = new ArrayList<>();

    @JsonProperty("quantidadeComentariosfilhos")
    public int getQuantidadeComentariosfilhos() {
        return comentariosFilhos.size();
    }

    @JsonProperty("quantidadeUseFulBy")
    public int getQuantidadeUseFulBy() {
        return usefulBy.size();
    }
}
