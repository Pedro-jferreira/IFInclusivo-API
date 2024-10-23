package com.example.IfGoiano.IfCoders.controller.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleProfessorDTO extends SimpleUsuarioDTO {
    private String formacao;

}
