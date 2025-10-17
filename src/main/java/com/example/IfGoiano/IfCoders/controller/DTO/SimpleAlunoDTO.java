package com.example.IfGoiano.IfCoders.controller.DTO;

import com.example.IfGoiano.IfCoders.entity.Enums.Curso;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleAlunoDTO extends SimpleUsuarioDTO {
    private Curso curso;
}