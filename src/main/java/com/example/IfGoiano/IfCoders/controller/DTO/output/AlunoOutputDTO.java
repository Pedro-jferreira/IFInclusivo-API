package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlunoOutputDTO extends UsuarioOutputDTO {
    private CursoInputDTO curso;
}
