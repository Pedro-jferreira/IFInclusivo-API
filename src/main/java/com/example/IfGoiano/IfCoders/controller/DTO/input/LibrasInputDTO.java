package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import lombok.Data;

@Data
public class LibrasInputDTO {
    private String palavra;
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;
    private Categorias categorias;
}
