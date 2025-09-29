package com.example.IfGoiano.IfCoders.controller.DTO.output;

import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessorOutputDTO extends UsuarioOutputDTO {
    private String formacao;
}
