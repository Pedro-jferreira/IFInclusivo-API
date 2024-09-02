package com.example.IfGoiano.IfCoders.controller.DTO.output;

import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class AlunoNapneOutputDTO extends AlunoOutputDTO{
    private String condicao;
    private String laudo;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;
    private String situacao;

}
