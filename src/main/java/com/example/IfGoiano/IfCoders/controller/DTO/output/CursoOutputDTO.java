package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import lombok.Data;


import java.util.List;
@Data
public class CursoOutputDTO {
    private Long id;
    private String nome;
    private List<SimpleAlunoDTO> alunos;

}
