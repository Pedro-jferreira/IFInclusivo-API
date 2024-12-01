package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleTopicoDTO {
    private Long id;
    private String titulo;
    private String tema;
    private String descripcion;
    private Categorias categoria;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private SimpleProfessorDTO professor;
}
