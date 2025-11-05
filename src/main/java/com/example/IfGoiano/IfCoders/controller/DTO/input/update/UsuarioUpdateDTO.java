package com.example.IfGoiano.IfCoders.controller.DTO.input.update;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nome;
    private Long matricula;
    private String biografia;
    private String imgPerfil;
}
