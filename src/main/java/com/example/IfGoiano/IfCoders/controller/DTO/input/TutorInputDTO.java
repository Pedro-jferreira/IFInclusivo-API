package com.example.IfGoiano.IfCoders.controller.DTO.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TutorInputDTO extends UsuarioInputDTO {
    private String especialidade;
}
