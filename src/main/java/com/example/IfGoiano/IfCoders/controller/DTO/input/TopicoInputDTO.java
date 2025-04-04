package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import lombok.Data;

@Data
public class TopicoInputDTO {
    private String titulo;
    private String descricao;
    private Categorias categoria;
}
