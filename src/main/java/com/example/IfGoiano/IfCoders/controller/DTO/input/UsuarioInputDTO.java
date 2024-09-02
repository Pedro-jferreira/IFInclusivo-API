package com.example.IfGoiano.IfCoders.controller.DTO.input;


import lombok.Data;

@Data
public class UsuarioInputDTO {
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
}
