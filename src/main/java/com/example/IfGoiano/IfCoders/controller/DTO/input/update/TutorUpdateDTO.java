package com.example.IfGoiano.IfCoders.controller.DTO.input.update;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TutorUpdateDTO extends UsuarioUpdateDTO {
    private String especialidade;

}
