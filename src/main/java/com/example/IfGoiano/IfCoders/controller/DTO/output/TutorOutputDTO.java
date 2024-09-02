package com.example.IfGoiano.IfCoders.controller.DTO.output;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TutorOutputDTO extends UsuarioOutputDTO{
    private String especialidade;

}
