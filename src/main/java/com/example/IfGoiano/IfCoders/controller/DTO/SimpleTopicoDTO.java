package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleTopicoDTO {
    private Long id;
    private String titulo;
    private String tema;
    private String descripcion;
    private Categorias categoria;
    private LocalDateTime dataCriacao;
    private SimpleProfessorDTO professor;
}
