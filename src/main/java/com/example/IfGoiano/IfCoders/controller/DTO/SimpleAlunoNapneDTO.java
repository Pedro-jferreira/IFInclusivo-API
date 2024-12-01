package com.example.IfGoiano.IfCoders.controller.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleAlunoNapneDTO extends SimpleAlunoDTO {
    private String condicao;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
}
