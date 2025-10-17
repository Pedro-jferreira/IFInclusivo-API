package com.example.IfGoiano.IfCoders.controller.DTO.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComentarioRequestDTO {

    @NotBlank(message = "O texto do comentário não pode estar vazio.")
    private String texto;
    private Long parentId;
    private Long usuarioMencionadoId;
}