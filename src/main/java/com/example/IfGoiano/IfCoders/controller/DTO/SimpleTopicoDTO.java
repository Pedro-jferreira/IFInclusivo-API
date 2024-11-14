package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import lombok.Data;

@Data
public class SimpleTopicoDTO {
    private Long id;
    private String tema;
    private Categorias descripcion;
    private String categoria;
    private SimpleProfessorDTO professor;
}
