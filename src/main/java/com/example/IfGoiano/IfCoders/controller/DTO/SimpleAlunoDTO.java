package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleAlunoDTO extends SimpleUsuarioDTO {
    private CursoInputDTO curso;
}