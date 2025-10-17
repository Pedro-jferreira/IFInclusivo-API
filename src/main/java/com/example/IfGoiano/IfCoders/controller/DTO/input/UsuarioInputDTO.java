package com.example.IfGoiano.IfCoders.controller.DTO.input;


import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioInputDTO {
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    private String imgPerfil;
}
