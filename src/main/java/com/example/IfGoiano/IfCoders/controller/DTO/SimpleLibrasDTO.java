package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import lombok.Data;

@Data
public class SimpleLibrasDTO {
    private Long id;
    private String palavra;
    private Categorias categorias;
}
