package com.example.IfGoiano.IfCoders.controller.DTO.output;


import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UsuarioOutputDTO {
    private Long id;
    private String nome;
    private String login;
    private Long matricula;
    private String biografia;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private ConfigAcblOutputDTO configAcessibilidadeEntity;
    private Set<Role> roles;
    private String token;
    private String userType;


}
