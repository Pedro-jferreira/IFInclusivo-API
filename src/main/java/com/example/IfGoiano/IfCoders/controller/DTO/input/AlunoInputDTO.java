package com.example.IfGoiano.IfCoders.controller.DTO.input;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlunoInputDTO extends UsuarioInputDTO{
    private CursoInputDTO curso;
}
