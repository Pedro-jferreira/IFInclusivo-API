package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TopicoOutputDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Categorias categoria;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleProfessorDTO professor;
    @JsonIgnore
    private List<SimplePublicacaoDTO> publicacoes = new ArrayList<>();
    @JsonProperty("quantidadePublicacoes")
    public int getQuantidadeComentarios() {
        return publicacoes.size();
    }
}
