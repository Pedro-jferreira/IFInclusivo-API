package com.example.IfGoiano.IfCoders.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "aluno_napne")
@Data
@DiscriminatorValue("ALUNONAPNE")
public class AlunoNapneEntity extends AlunoEntity {

    @NotNull   @Column(nullable = false)
    private String condicao;
    @NotNull   @Column(nullable = false)
    private String laudo;
    @NotNull   @Column(nullable = false)
    private String necessidadeEspecial;
    @NotNull   @Column(nullable = false)
    private String necessidadeEscolar;
    @NotNull   @Column(nullable = false)
    private String acompanhamento;
    @NotNull   @Column(nullable = false)
    private String situacao;

}
