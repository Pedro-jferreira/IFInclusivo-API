package com.example.IfGoiano.IfCoders.controller.DTO.input;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlunoNapneInputDTO extends AlunoInputDTO{
    private String condicao;
    private String laudo;
    private String necessidadeEspecial;
    private String necessidadeEscolar;
    private String acompanhamento;
    private String situacao;
}
