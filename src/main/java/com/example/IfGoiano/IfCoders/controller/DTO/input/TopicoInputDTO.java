package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import lombok.Data;

@Data
public class TopicoInputDTO {
    private SimpleProfessorDTO professor;
    private String tema;
    private String descripcion;
    private String categoria;

}
