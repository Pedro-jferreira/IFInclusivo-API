package com.example.IfGoiano.IfCoders.controller.DTO.input.update;

import com.example.IfGoiano.IfCoders.entity.Enums.Curso;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlunoUpdateDTO extends  UsuarioUpdateDTO{
    private Curso curso;

}
