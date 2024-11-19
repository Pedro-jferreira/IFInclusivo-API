package com.example.IfGoiano.IfCoders.controller.DTO;

import lombok.Data;

@Data
public class SimpleTopicoDTO {
    private Long id;
    private String tema;
    private String descripcion;
    private String categoria;
    private SimpleProfessorDTO professor;
}
